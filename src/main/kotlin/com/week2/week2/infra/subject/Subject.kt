package com.week2.week2.infra.subject

import com.week2.week2.infra.member.Member
import jakarta.persistence.*

@Entity
class Subject (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    val name: String,

    @ManyToOne(fetch = FetchType.LAZY)
    val teacher: Member
)