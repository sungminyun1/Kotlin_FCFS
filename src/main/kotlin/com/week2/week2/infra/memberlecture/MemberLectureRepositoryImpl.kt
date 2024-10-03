package com.week2.week2.infra.memberlecture

import com.week2.week2.domain.lecture.MemberLectureRepository
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
}