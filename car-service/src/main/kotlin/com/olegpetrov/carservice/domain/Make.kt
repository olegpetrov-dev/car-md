package com.olegpetrov.carservice.domain

import jakarta.persistence.*
import lombok.EqualsAndHashCode

@Entity
@Table(name = "makes")
@EqualsAndHashCode(of = ["id"])
class Make(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,
    var name: String
)
