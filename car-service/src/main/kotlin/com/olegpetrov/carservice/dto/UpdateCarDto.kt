package com.olegpetrov.carservice.dto

import com.olegpetrov.carservice.domain.enums.Drivetrain
import com.olegpetrov.carservice.domain.enums.Fuel
import com.olegpetrov.carservice.domain.enums.Transmission
import org.springframework.web.multipart.MultipartFile

data class UpdateCarDto(
    val make: String?,
    val model: String?,
    val price: Double?,
    val year: Int?,
    val mileage: Int?,
    val fuel: Fuel?,
    val transmission: Transmission?,
    val drive: Drivetrain?,
    val color: String?,
    val engine: String?,
    val hp: Int?,
    val body: String?,
    val places: Int?,
    val description: String?,
    val locationAddress: String?,
    val managerId: Long?,
    val photos: List<MultipartFile>? = mutableListOf()
)

