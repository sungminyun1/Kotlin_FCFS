package com.week2.week2.infra.lecture

import jakarta.persistence.LockModeType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Lock
import org.springframework.data.jpa.repository.Query

interface LectureJpaRepository : JpaRepository<Lecture, Long> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select l from Lecture l join fetch l.subject ls " +
            "join fetch ls.teacher where l.id = :id")
    fun findByIdWithSubjectAndTeacher(id: Long): Lecture?

    @Query("select l from Lecture l join fetch l.subject ls " +
            "join fetch ls.teacher where l.date = :date")
    fun findAllByDateWithSubjectAndTeacher(date: String): List<Lecture>
}