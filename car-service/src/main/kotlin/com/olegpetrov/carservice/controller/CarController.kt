package com.olegpetrov.carservice.controller

import com.olegpetrov.carservice.dto.*
import com.olegpetrov.carservice.service.CarService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/cars")
class CarController(private val carService: CarService) {

    @GetMapping
    fun searchCars(
        searchDto: SearchCarDto
    ): PageDto<CarDto> {
        return carService.searchCars(searchDto);
    }

    @GetMapping("/{id}")
    fun getCarById(@PathVariable id: Long): CarDto {
        return carService.getCarById(id)
    }

    @PostMapping
    fun createCar(@ModelAttribute createCarDto: CreateCarDto) {
        return carService.createCar(createCarDto)
    }

    @PatchMapping("/{id}")
    fun updateCar(@PathVariable("id") id: Long, @ModelAttribute updateCarDto: UpdateCarDto) {
        return carService.updateCar(id, updateCarDto)
    }
}
