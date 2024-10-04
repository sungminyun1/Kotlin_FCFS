package com.week2.week2.infra.memberlecture

import com.week2.week2.infra.lecture.Lecture
import com.week2.week2.infra.member.Member
import jakarta.persistence.*

@Entity
class MemberLecture (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    var lecture: Lecture,

    @ManyToOne(fetch = FetchType.LAZY)
    var member: Member,
){
}
