package com.olegpetrov.carservice.domain

import jakarta.persistence.*
import lombok.EqualsAndHashCode

@Entity
@Table(name = "models")
@EqualsAndHashCode(of = ["id"])
class Model(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "make_id", nullable = false)
    var make: Make,

    var name: String
)

