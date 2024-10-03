package com.week2.week2.interfaces.api.lecture.dto

import com.week2.week2.infra.lecture.Lecture

data class LectureResponse(
    val id: Long,
    val name: String,
    val teacher: String
){
    companion object{
        fun of(lecture: Lecture): LectureResponse{
            return LectureResponse(
                lecture.id,
                lecture.subject.name,
                lecture.subject.teacher.name
            )
        }
    }
}
