package com.week2.week2.infra.memberlecture

import org.springframework.data.jpa.repository.JpaRepository

interface MemberLectureJpaRepository: JpaRepository<MemberLecture, Long> {
}