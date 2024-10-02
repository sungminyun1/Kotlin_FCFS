package com.week2.week2.domain.Lecture

import com.week2.week2.domain.Member.Member
import jakarta.persistence.*

@Entity
class Lecture (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    val name: String,

    @ManyToOne(fetch = FetchType.LAZY)
    val teacher: Member
)