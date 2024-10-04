package com.week2.week2.domain.member

import com.week2.week2.infra.lecture.Lecture
import org.springframework.stereotype.Service

@Service
class MemberService(
    private val memberRepository: MemberRepository,
    private val memberLectureRepository: MemberLectureRepository
) {

    fun findUserEnrollments(userId: Long): List<Lecture> {
        val member = memberRepository.findById(userId)
            ?: throw RuntimeException("User not found")

        val memberLectures = memberLectureRepository.findAllByMember(member)

        return memberLectures.map{ it.lecture}
    }
}