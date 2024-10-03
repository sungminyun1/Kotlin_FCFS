package com.week2.week2.domain.lecture

import com.week2.week2.infra.lecture.Lecture
import jakarta.persistence.LockModeType
import org.springframework.data.jpa.repository.Lock

interface LectureRepository {

    fun findByIdWithSubjectAndTeacher(id: Long): Lecture?

    fun findAllByDateWithSubjectAndTeacher(date:String): List<Lecture>

    fun save(lecture: Lecture): Lecture
}