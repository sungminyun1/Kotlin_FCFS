package com.week2.week2.domain.lecture

import com.week2.week2.TestSupport
import com.week2.week2.domain.lecture.dto.LectureEnrollServiceRequest
import com.week2.week2.domain.member.MemberLectureRepository
import com.week2.week2.domain.member.MemberRepository
import com.week2.week2.infra.lecture.Lecture
import com.week2.week2.infra.member.Member
import com.week2.week2.infra.subject.Subject
import com.week2.week2.infra.subject.SubjectJpaRepository
import jakarta.transaction.Transactional
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import java.util.concurrent.CountDownLatch
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

//@Transactional
class LectureServiceIntegrateTest(
    @Autowired private val lectureService: LectureService,
    @Autowired private val memberRepository: MemberRepository,
    @Autowired private val lectureRepository: LectureRepository,
    @Autowired private val subjectJpaRepository: SubjectJpaRepository,
    @Autowired private val memberLectureRepository: MemberLectureRepository,
) : TestSupport() {

    private lateinit var executorService: ExecutorService

    @BeforeEach
    fun setup() {
        executorService = Executors.newFixedThreadPool(50)
    }

    @Test
    @DisplayName("같은 강의의 수강신청은 최대 30명이 가능하다")
    fun testLectureMaxCap() {
        //given
        val teacher = Member(name = "teacher")
        memberRepository.save(teacher)
        val subject = Subject(name = "subject 1", teacher = teacher)
        subjectJpaRepository.save(subject)
        val lecture = Lecture(subject = subject, date = "241001", capacity = Lecture.MAX_CAPACITY)
        lectureRepository.save(lecture)
        for (i in 1..30) {
            val tmpMember = Member(name = "student")
            memberRepository.save(tmpMember)
            val tmpRequest = LectureEnrollServiceRequest(tmpMember.id!!, lecture.id!!)
            lectureService.enroll(tmpRequest)
        }
        val student = Member(name = "student")
        memberRepository.save(student)
        val request = LectureEnrollServiceRequest(student.id!!, lecture.id!!)

        //when then
        Assertions.assertThatThrownBy { lectureService.enroll(request) }
            .isInstanceOf(RuntimeException::class.java)
            .hasMessage("수업 정원을 초과했습니다")
    }

    @Test
    @DisplayName("동시에 40명이 수강신청을 할 경우 선착선 30명만 성공한다")
    fun testConcurrentRequest() {
        //given
        val teacher = Member(name = "teacher")
        memberRepository.save(teacher)
        val subject = Subject(name = "subject 1", teacher = teacher)
        subjectJpaRepository.save(subject)
        val lecture = Lecture(subject = subject, date = "241001", capacity = Lecture.MAX_CAPACITY)
        lectureRepository.save(lecture)

        val numberOfThreads = 40L
        val startLatch = CountDownLatch(1)
        val finishLatch = CountDownLatch(numberOfThreads.toInt())
        for (i in 1..numberOfThreads) {
            val tmpMember = Member(name = "student")
            memberRepository.save(tmpMember)
        }

        for(i in 1L..numberOfThreads) {
            executorService.submit {
                try{
                    startLatch.await()

                    val request = LectureEnrollServiceRequest(i, lecture.id!!)
                    val response = lectureService.enroll(request)

                }finally {
                    finishLatch.countDown()
                }
            }
        }

        startLatch.countDown()

        executorService.shutdown()
        executorService.awaitTermination(1, TimeUnit.MINUTES)

        //when
        val resultCount = memberLectureRepository.findAllByLecture(lecture)

        //then
        assertThat(resultCount.size).isEqualTo(Lecture.MAX_CAPACITY)
    }
}