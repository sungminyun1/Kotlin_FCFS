package com.week2.week2.domain.member

import com.week2.week2.TestSupport
import com.week2.week2.infra.lecture.Lecture
import com.week2.week2.infra.member.Member
import com.week2.week2.infra.memberlecture.MemberLecture
import com.week2.week2.infra.subject.Subject
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.mock.mockito.MockBean

class MemberServiceUnitTest(
    @Autowired val memberService: MemberService,
) : TestSupport() {

    @MockBean
    lateinit var memberRepository: MemberRepository

    @MockBean
    lateinit var memberLectureRepository: MemberLectureRepository

    lateinit var student: Member
    lateinit var teacher: Member
    lateinit var subject: Subject
    lateinit var lecture: Lecture
    lateinit var memberLecture: MemberLecture

    @BeforeEach
    fun setup() {
        student = Member(1,"student")
        teacher = Member(2,"teacher")
        subject = Subject(1, "subject", teacher)
        lecture = Lecture(1, subject,"241001", Lecture.MAX_CAPACITY)
        memberLecture = MemberLecture(1, lecture, student)
    }

    @Test
    @DisplayName("자신이 신천한 강의 내역을 조회한다")
    fun testMyEnrollment() {
        //given
        val userId = 1L;
        `when`(memberRepository.findById(anyLong())).thenReturn(student)
        `when`(memberLectureRepository.findAllByMember(student)).thenReturn(listOf(memberLecture))

        //when
        val result = memberService.findUserEnrollments(userId)

        //then
        assertThat(result.size).isEqualTo(1)
    }
}