package com.week2.week2.infra.lecture

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface LectureJpaRepository : JpaRepository<Lecture, Long> {
    @Query("select l from Lecture l join fetch l.subject ls " +
            "join fetch ls.teacher where l.id = :id")
    fun findByIdWithSubjectAndTeacher(id: Long): Lecture?
}