package com.week2.week2.interfaces.api.member

import com.week2.week2.domain.member.MemberService
import com.week2.week2.interfaces.api.lecture.dto.LectureResponse
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
@ResponseBody
@RequestMapping("api/v1/member")
class MemberController(
    private val memberService: MemberService
) {

    @GetMapping("/{userId}/enrollment")
    fun getMemberEnrollment(
        @PathVariable("userId") userId: Long,
    ): List<LectureResponse> {
        val results = memberService.findUserEnrollments(userId)
        return results.map { LectureResponse.of(it) }
    }

}