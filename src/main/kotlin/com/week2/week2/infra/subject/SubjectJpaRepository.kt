package com.week2.week2.infra.subject

import org.springframework.data.jpa.repository.JpaRepository

interface SubjectJpaRepository: JpaRepository<Subject, Long> {
}