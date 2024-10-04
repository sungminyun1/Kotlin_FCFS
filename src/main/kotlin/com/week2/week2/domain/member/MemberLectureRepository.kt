package com.week2.week2.domain.member

import com.week2.week2.infra.member.Member
import com.week2.week2.infra.memberlecture.MemberLecture

interface MemberLectureRepository {
    fun save(memberLecture: MemberLecture): MemberLecture
    fun findById(id: Long): MemberLecture?
    fun findAllByMember(member: Member): List<MemberLecture>
}