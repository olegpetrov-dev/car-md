package com.olegpetrov.carservice.domain.builder

import com.olegpetrov.carservice.domain.Car
import com.olegpetrov.carservice.domain.Manager
import com.olegpetrov.carservice.domain.Model
import com.olegpetrov.carservice.dto.CreateCarDto
import com.olegpetrov.carservice.dto.UpdateCarDto
import org.springframework.stereotype.Component

@Component
class CarBuilder {

    fun buildCar(createCarDto: CreateCarDto, model: Model, manager: Manager, photoUrls: List<String>): Car {
        var car =
            Car(
                model = model,
                price = createCarDto.price,
                year = createCarDto.year,
                mileage = createCarDto.mileage,
                transmission = createCarDto.transmission,
                fuel = createCarDto.fuel,
                drive = createCarDto.drive,
                color = createCarDto.color,
                engine = createCarDto.engine,
                hp = createCarDto.hp,
                body = createCarDto.body,
                places = createCarDto.places,
                description = createCarDto.description,
                manager = manager,
                photos = photoUrls,
                locationAddress = createCarDto.locationAddress
            )
        return car;
    }

    fun updateCar(
        car: Car,
        updateCarDto: UpdateCarDto,
        model: Model?,
        manager: Manager?,
        photoUrls: List<String>?
    ) {
        car.model = model ?: car.model
        car.price = updateCarDto.price ?: car.price
        car.year = updateCarDto.year ?: car.year
        car.mileage = updateCarDto.mileage ?: car.mileage
        car.transmission = updateCarDto.transmission ?: car.transmission
        car.fuel = updateCarDto.fuel ?: car.fuel
        car.drive = updateCarDto.drive ?: car.drive
        car.color = updateCarDto.color ?: car.color
        car.engine = updateCarDto.engine ?: car.engine
        car.hp = updateCarDto.hp ?: car.hp
        car.body = updateCarDto.body ?: car.body
        car.places = updateCarDto.places ?: car.places
        car.description = updateCarDto.description ?: car.description
        car.manager = manager ?: car.manager
        car.photos = photoUrls ?: car.photos
        car.locationAddress = updateCarDto.locationAddress ?: car.locationAddress
    }
}
