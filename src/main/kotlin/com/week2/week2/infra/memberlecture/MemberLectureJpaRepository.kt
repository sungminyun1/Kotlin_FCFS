package com.week2.week2.infra.memberlecture

import com.week2.week2.infra.lecture.Lecture
import com.week2.week2.infra.member.Member
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface MemberLectureJpaRepository: JpaRepository<MemberLecture, Long> {
    @Query("select ml from MemberLecture ml " +
            "join fetch ml.lecture l  join fetch l.subject ls " +
            "join fetch ls.teacher where ml.member = :member")
    fun findAllByMember(member: Member): List<MemberLecture>

    fun findAllByLecture(lecture: Lecture): List<MemberLecture>

    fun existsByLectureAndMember(lecture: Lecture, member: Member): Boolean
}