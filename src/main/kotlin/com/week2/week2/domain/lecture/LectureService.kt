package com.week2.week2.domain.lecture

import com.week2.week2.domain.lecture.dto.LectureEnrollServiceRequest
import com.week2.week2.domain.member.MemberLectureRepository
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
    private val memberLectureRepository: MemberLectureRepository,
){

    val logger = LoggerFactory.getLogger(LectureService::class.java)

    @Transactional
    fun enroll(
        request: LectureEnrollServiceRequest
    ): Lecture {

        val targetLecture = lectureRepository.findByIdWithSubjectAndTeacher(request.lectureId)
            ?: throw RuntimeException("Lecture with id ${request.lectureId} not found")


        val requestMember = memberRepository.findById(request.userId)
            ?: throw RuntimeException("Member with id ${request.lectureId} not found")

        if(memberLectureRepository.existsByLectureAndMember(requestMember, targetLecture)){
            logger.warn("이미 유저가 신청한 강의입니다")
            throw RuntimeException("이미 유저가 신청한 강의입니다")
        }
        targetLecture.enrollMember(requestMember)

        return targetLecture
    }

    fun getLectureListByDate(date: String): List<Lecture> {
        return lectureRepository.findAllByDateWithSubjectAndTeacher(date)
    }
}