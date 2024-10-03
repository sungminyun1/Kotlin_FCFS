package com.week2.week2.domain.lecture

import com.week2.week2.infra.lecture.Lecture

interface LectureRepository {
    fun findByIdWithSubjectAndTeacher(id: Long): Lecture?
}