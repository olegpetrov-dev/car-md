package com.olegpetrov.carservice.domain

import jakarta.persistence.*

@Entity
@Table(name = "managers")
class Manager(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    var name: String,

    var phone: String
)
