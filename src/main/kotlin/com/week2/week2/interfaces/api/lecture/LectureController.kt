package com.week2.week2.interfaces.api.lecture

import com.week2.week2.domain.lecture.LectureService
import com.week2.week2.interfaces.api.lecture.dto.LectureEnrollRequest
import com.week2.week2.interfaces.api.lecture.dto.LectureResponse
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
@ResponseBody
@RequestMapping("api/v1/lecture")
class LectureController(
    private val lectureService: LectureService
) {

    @GetMapping("/{date}")
    fun getLectureList(@PathVariable date: String) : List<LectureResponse> {
        val results = lectureService.getLectureListByDate(date)
        return results.map{LectureResponse.of(it)}
    }

    @PostMapping
    fun enrollLecture(
        @RequestBody request: LectureEnrollRequest
    ): LectureResponse {
        val resultLecture = lectureService.enroll(request.toServiceRequest())
        return LectureResponse.of(resultLecture)
    }
}