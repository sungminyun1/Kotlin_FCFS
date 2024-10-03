package com.week2.week2.infra.memberlecture

import com.week2.week2.domain.member.MemberLectureRepository
import com.week2.week2.infra.lecture.Lecture
import com.week2.week2.infra.member.Member
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

@Repository
class MemberLectureRepositoryImpl(
    val memberLectureJpaRepository: MemberLectureJpaRepository
) : MemberLectureRepository {
    override fun save(memberLecture: MemberLecture): MemberLecture {
        return memberLectureJpaRepository.save(memberLecture)
    }

    override fun findById(id: Long): MemberLecture? {
        return memberLectureJpaRepository.findByIdOrNull(id)
    }

    override fun findAllByMember(member: Member): List<MemberLecture> {
        return memberLectureJpaRepository.findAllByMember(member)
    }

    override fun findAllByLecture(lecture: Lecture): List<MemberLecture> {
        return memberLectureJpaRepository.findAllByLecture(lecture)

    }
}