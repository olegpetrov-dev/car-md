package com.olegpetrov.carservice.dto

import com.olegpetrov.carservice.domain.enums.Drivetrain
import com.olegpetrov.carservice.domain.enums.Fuel
import com.olegpetrov.carservice.domain.enums.Transmission

data class CarDto(
    val id: Long,
    val make: String,
    val model: String,
    val price: Double,
    val year: Int,
    val mileage: Int,
    val fuel: Fuel,
    val transmission: Transmission,
    val drive: Drivetrain,
    val color: String,
    val engine: String,
    val hp: Int,
    val body: String,
    val places: Int,
    val description: String,
    val locationAddress: String,
    val manager: ManagerDto?,
    val photos: List<String>? = mutableListOf()
)

