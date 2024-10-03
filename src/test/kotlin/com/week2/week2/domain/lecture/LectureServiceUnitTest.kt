package com.week2.week2.domain.lecture

import com.week2.week2.TestSupport
import com.week2.week2.domain.lecture.dto.LectureEnrollServiceRequest
import com.week2.week2.infra.lecture.Lecture
import com.week2.week2.infra.member.Member
import com.week2.week2.infra.subject.Subject
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.anyLong
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.mock.mockito.MockBean


class LectureServiceUnitTest(
    @Autowired val lectureService: LectureService,
) : TestSupport() {

    @MockBean
    lateinit var memberRepository: MemberRepository

    @MockBean
    lateinit var lectureRepository: LectureRepository

    lateinit var student: Member
    lateinit var teacher: Member
    lateinit var subject: Subject
    lateinit var lecture: Lecture

    @BeforeEach
    fun setup() {
        student = Member(1,"student")
        teacher = Member(2,"teacher")
        subject = Subject(1, "subject", teacher)
        lecture = Lecture(1, subject,"241001", Lecture.MAX_CAPACITY)
    }

    @Test
    @DisplayName("사용자 아이디와 강의 아이디로 수강 신청을 한다")
    fun testEnrollLecture() {
        //given
        val userId = 1L;
        val lectureId = 1L;
        val request = LectureEnrollServiceRequest(userId, lectureId)
        `when`(memberRepository.findById(anyLong())).thenReturn(student)
        `when`(lectureRepository.findByIdWithSubjectAndTeacher(anyLong())).thenReturn(lecture)

        //when
        val result = lectureService.enroll(request)

        assertThat(result.capacity).isEqualTo(Lecture.MAX_CAPACITY - 1)
    }

    @Test
    @DisplayName("잘못된 사용자 아이디로 신청하면 에러가 발생한다")
    fun testEnrollLectureWithWrongUser() {
        //given
        val userId = 1L;
        val lectureId = 1L;
        val request = LectureEnrollServiceRequest(userId, lectureId)
        `when`(memberRepository.findById(anyLong())).thenReturn(null)
        `when`(lectureRepository.findByIdWithSubjectAndTeacher(anyLong())).thenReturn(lecture)

        //when then
        assertThatThrownBy { lectureService.enroll(request) }
            .isInstanceOf(RuntimeException::class.java)
    }

    @Test
    @DisplayName("잘못된 강의 아이디로 신청하면 에러가 발생한다")
    fun testEnrollLectureWithWrongLecture() {
        //given
        val userId = 1L;
        val lectureId = 1L;
        val request = LectureEnrollServiceRequest(userId, lectureId)
        `when`(memberRepository.findById(anyLong())).thenReturn(student)
        `when`(lectureRepository.findByIdWithSubjectAndTeacher(anyLong())).thenReturn(null)

        //when then
        assertThatThrownBy { lectureService.enroll(request) }
            .isInstanceOf(RuntimeException::class.java)
    }
}