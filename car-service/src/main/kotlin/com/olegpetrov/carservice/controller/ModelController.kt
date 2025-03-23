package com.olegpetrov.carservice.controller

import com.olegpetrov.carservice.dto.CreateModelDto
import com.olegpetrov.carservice.service.ModelService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/models")
class ModelController(private val modelService: ModelService) {

    @GetMapping
    fun getModelsForMake(@RequestParam make: String): List<String> {
        return modelService.getModelsForMake(make)
    }

    @PostMapping
    fun createMake(@RequestBody createModelDto: CreateModelDto) {
        modelService.createModel(createModelDto)
    }

}
