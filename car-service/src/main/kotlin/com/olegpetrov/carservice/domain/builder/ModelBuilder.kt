package com.olegpetrov.carservice.domain.builder

import com.olegpetrov.carservice.domain.Make
import com.olegpetrov.carservice.domain.Model
import org.springframework.stereotype.Component

@Component
class ModelBuilder {
    fun build(name: String, make: Make): Model {
        return Model(name = name, make = make)
    }
}
