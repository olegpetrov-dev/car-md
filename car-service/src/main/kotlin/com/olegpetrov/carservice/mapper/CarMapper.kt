package com.olegpetrov.carservice.mapper

import com.olegpetrov.carservice.domain.Car
import com.olegpetrov.carservice.domain.options.CarIncludeOptions
import com.olegpetrov.carservice.dto.CarDto
import org.springframework.stereotype.Component

@Component
class CarMapper(private val managerMapper: ManagerMapper) : BaseMapper<Car, CarDto> {

    override fun toDto(entity: Car): CarDto {
        return toDto(entity, CarIncludeOptions())
    }

    fun toDto(car: Car, options: CarIncludeOptions): CarDto {

        return CarDto(
            car.id,
            car.model.make.name,
            car.model.name,
            car.price,
            car.year,
            car.mileage,
            car.fuel,
            car.transmission,
            car.drive,
            car.color,
            car.engine,
            car.hp,
            car.body,
            car.places,
            car.description,
            car.locationAddress,
            if (options.includeManager) managerMapper.toDto(car.manager) else null,
            if (options.includePhotos) car.photos else null
        )
    }
}
