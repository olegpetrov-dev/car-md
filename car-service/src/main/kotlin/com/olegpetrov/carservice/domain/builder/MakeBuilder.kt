package com.olegpetrov.carservice.domain.builder

import com.olegpetrov.carservice.domain.Make
import com.olegpetrov.carservice.dto.CreateMakeDto
import org.springframework.stereotype.Component

@Component
class MakeBuilder {
    fun build(createMakeDto: CreateMakeDto): Make {
        return Make(name = createMakeDto.name)
    }
}
