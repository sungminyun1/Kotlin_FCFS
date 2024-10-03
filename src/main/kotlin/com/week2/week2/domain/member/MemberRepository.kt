package com.week2.week2.domain.member

import com.week2.week2.infra.member.Member

interface MemberRepository {
    fun findById(memberId: Long): Member?
    fun save(member: Member): Member
}