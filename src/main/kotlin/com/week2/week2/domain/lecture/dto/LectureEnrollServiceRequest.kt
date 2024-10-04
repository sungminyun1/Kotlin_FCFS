package com.week2.week2.domain.lecture.dto

data class LectureEnrollServiceRequest(
    val userId: Long,
    val lectureId: Long,
) {
}