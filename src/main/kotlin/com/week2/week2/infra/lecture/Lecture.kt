package com.week2.week2.infra.lecture

import com.week2.week2.infra.member.Member
import com.week2.week2.infra.memberlecture.MemberLecture
import com.week2.week2.infra.subject.Subject
import jakarta.persistence.*

@Entity
class Lecture (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @ManyToOne(fetch = FetchType.LAZY)
    val subject: Subject,

    val date: String,

    var capacity: Int,

    @OneToMany(mappedBy = "lecture", cascade = [CascadeType.ALL], orphanRemoval = true)
    var members: MutableList<MemberLecture> = mutableListOf()

){

    companion object {
        val MAX_CAPACITY = 30
    }

    fun enrollMember(member: Member): Lecture {
        if(capacity < 1) throw RuntimeException("수업 정원을 초과했습니다")
        capacity--
        members.add(MemberLecture(
            lecture = this,
            member = member,
        ))
        return this
    }
}