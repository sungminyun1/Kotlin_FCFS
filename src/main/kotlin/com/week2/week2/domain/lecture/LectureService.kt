package com.week2.week2.domain.lecture

import com.week2.week2.domain.lecture.dto.LectureEnrollServiceRequest
import com.week2.week2.infra.lecture.Lecture
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class LectureService (
    private val lectureRepository: LectureRepository,
    private val memberRepository: MemberRepository,
    private val memberLectureRepository: MemberLectureRepository
){

    @Transactional
    fun enroll(
        request: LectureEnrollServiceRequest
    ): Lecture {
        val targetLecture = lectureRepository.findByIdWithSubjectAndTeacher(request.lectureId)
            ?: throw RuntimeException("Lecture with id ${request.lectureId} not found")

        val requestMember = memberRepository.findById(request.userId)
            ?: throw RuntimeException("Member with id ${request.lectureId} not found")

        targetLecture.enrollMember(requestMember)

        return targetLecture
    }

    fun getLectureListByDate(date: String): List<Lecture> {
        return lectureRepository.findAllByDateWithSubjectAndTeacher(date)
    }
}