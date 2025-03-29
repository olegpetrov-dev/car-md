package com.olegpetrov.carservice.dto

import com.olegpetrov.carservice.domain.enums.Transmission

data class SearchCarDto(
    val id: Long? = null,
    val ids: List<Long>? = null,
    val make: String? = null,
    val model: String? = null,
    val minPrice: Double? = null,
    val maxPrice: Double? = null,
    val minYear: Int? = null,
    val maxYear: Int? = null,
    val minMileage: Int? = null,
    val maxMileage: Int? = null,
    val transmission: Transmission? = null,
    val page: Int = 1,
    val pageSize: Int = 20,
    val sortBy: List<String>? = null
)
