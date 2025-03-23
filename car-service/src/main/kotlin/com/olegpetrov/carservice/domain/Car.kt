package com.olegpetrov.carservice.domain

import com.olegpetrov.carservice.domain.enums.Drivetrain
import com.olegpetrov.carservice.domain.enums.Fuel
import com.olegpetrov.carservice.domain.enums.Transmission
import jakarta.persistence.*
import jakarta.persistence.EnumType.STRING

@Entity
@Table(name = "cars")
class Car(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "model_id")
    var model: Model,

    var price: Double,
    var year: Int,
    var mileage: Int,

    @Enumerated(STRING)
    var fuel: Fuel,

    @Enumerated(STRING)
    var transmission: Transmission,

    @Enumerated(STRING)
    var drive: Drivetrain,

    var color: String,
    var engine: String,
    var hp: Int,
    var body: String,
    var places: Int,
    var description: String,
    var locationAddress: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id")
    var manager: Manager,

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "car_photos", joinColumns = [JoinColumn(name = "car_id")])
    @Column(name = "photo_url")
    var photos: List<String> = mutableListOf()

)

