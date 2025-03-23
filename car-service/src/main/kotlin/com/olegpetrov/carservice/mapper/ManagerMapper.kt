package com.olegpetrov.carservice.mapper

import com.olegpetrov.carservice.domain.Manager
import com.olegpetrov.carservice.dto.ManagerDto
import org.springframework.stereotype.Component

@Component
class ManagerMapper : BaseMapper<Manager, ManagerDto> {

    override fun toDto(entity: Manager): ManagerDto {
        return ManagerDto(entity.id, entity.name, entity.phone)
    }
}
