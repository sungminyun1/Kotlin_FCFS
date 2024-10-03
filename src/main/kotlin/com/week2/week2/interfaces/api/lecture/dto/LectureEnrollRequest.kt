package com.week2.week2.interfaces.api.lecture.dto

import com.week2.week2.domain.lecture.dto.LectureEnrollServiceRequest

data class LectureEnrollRequest(
    val userId: Long,
    val lectureId: Long,
) {
    fun toServiceRequest(): LectureEnrollServiceRequest {
        return LectureEnrollServiceRequest(userId, lectureId)
    }
}