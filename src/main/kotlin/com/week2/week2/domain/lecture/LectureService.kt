package com.week2.week2.domain.lecture

import com.week2.week2.domain.lecture.dto.LectureEnrollServiceRequest
import com.week2.week2.domain.member.MemberRepository
import com.week2.week2.infra.lecture.Lecture

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Isolation
import org.springframework.transaction.annotation.Transactional

@Service
class LectureService (
    private val lectureRepository: LectureRepository,
    private val memberRepository: MemberRepository,
){

    val logger = LoggerFactory.getLogger(LectureService::class.java)

    @Transactional
    fun enroll(
        request: LectureEnrollServiceRequest
    ): Lecture {
        logger.info("arrived " +Thread.currentThread().id + " "  + System.nanoTime())

        val targetLecture = lectureRepository.findByIdWithSubjectAndTeacher(request.lectureId)
            ?: throw RuntimeException("Lecture with id ${request.lectureId} not found")
        logger.info("getLecture " +Thread.currentThread().id + " "  + System.nanoTime())

        val requestMember = memberRepository.findById(request.userId)
            ?: throw RuntimeException("Member with id ${request.lectureId} not found")

        targetLecture.enrollMember(requestMember)

        logger.info("end " +Thread.currentThread().id + " "  + System.nanoTime())
        return targetLecture
    }

    fun getLectureListByDate(date: String): List<Lecture> {
        return lectureRepository.findAllByDateWithSubjectAndTeacher(date)
    }
}