package com.olegpetrov.carservice.domain.options

data class CarIncludeOptions(
    val includeManager: Boolean = false,
    val includePhotos: Boolean = true
)
