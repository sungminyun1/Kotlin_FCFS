package com.week2.week2.infra.lecture

import com.week2.week2.domain.lecture.LectureRepository
import org.springframework.stereotype.Repository

@Repository
class LectureRepositoryImpl(
    val jpaRepository : LectureJpaRepository
) : LectureRepository {
    override fun findByIdWithSubjectAndTeacher(id: Long): Lecture? {
        return jpaRepository.findByIdWithSubjectAndTeacher(id)
    }

    override fun findAllByDateWithSubjectAndTeacher(date: String): List<Lecture> {
        return jpaRepository.findAllByDateWithSubjectAndTeacher(date)
    }
}