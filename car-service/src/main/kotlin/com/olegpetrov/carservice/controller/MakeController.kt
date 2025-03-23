package com.olegpetrov.carservice.controller

import com.olegpetrov.carservice.dto.CreateMakeDto
import com.olegpetrov.carservice.service.MakeService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/makes")
class MakeController(private val makeService: MakeService) {

    @GetMapping
    fun getAllMakes(): List<String> {
        return makeService.getAllMakes()
    }

    @PostMapping
    fun createMake(@RequestBody createMakeDto: CreateMakeDto) {
        makeService.createMake(createMakeDto)
    }

}
