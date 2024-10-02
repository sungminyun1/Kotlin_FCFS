package com.week2.week2.domain.MemberLectureDetail

import com.week2.week2.domain.LectureDetail.LectureDetail
import com.week2.week2.domain.Member.Member
import jakarta.persistence.*

@Entity
class MemberLectureDetail (

    @EmbeddedId
    val id: MemberLectureKey
){
}

@Embeddable
class MemberLectureKey(
    @ManyToOne(fetch = FetchType.LAZY)
    var lectureDetail: LectureDetail,

    @ManyToOne(fetch = FetchType.LAZY)
    var member: Member,
)