package com.week2.week2.domain.lecture

import com.week2.week2.infra.memberlecture.MemberLecture

interface MemberLectureRepository {
    fun save(memberLecture: MemberLecture): MemberLecture
    fun findById(id: Long): MemberLecture?
}