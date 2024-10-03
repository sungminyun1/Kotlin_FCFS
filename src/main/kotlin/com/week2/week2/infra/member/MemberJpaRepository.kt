package com.week2.week2.infra.member

import org.springframework.data.jpa.repository.JpaRepository

interface MemberJpaRepository: JpaRepository<Member, Long> {
}