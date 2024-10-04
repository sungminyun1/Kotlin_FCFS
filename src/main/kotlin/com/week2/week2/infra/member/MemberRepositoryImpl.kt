package com.week2.week2.infra.member

import com.week2.week2.domain.member.MemberRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

@Repository
class MemberRepositoryImpl(
    val memberJpaRepository: MemberJpaRepository
): MemberRepository {
    override fun findById(memberId: Long): Member? {
        return memberJpaRepository.findByIdOrNull(memberId)
    }
}