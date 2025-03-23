package com.olegpetrov.carservice.controller

import com.olegpetrov.carservice.dto.ManagerDto
import com.olegpetrov.carservice.dto.UpsertManagerDto
import com.olegpetrov.carservice.service.ManagerService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/managers")
class ManagerController(private val managerService: ManagerService) {

    @GetMapping
    fun getAllManagers(): List<ManagerDto> {
        return managerService.getAllManagers()
    }

    @PostMapping
    fun createManager(@RequestBody createManagerDto: UpsertManagerDto) {
        managerService.createManager(createManagerDto)
    }

}
