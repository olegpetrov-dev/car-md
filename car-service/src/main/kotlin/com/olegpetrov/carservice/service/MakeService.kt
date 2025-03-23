package com.olegpetrov.carservice.service

import com.olegpetrov.carservice.domain.Make
import com.olegpetrov.carservice.domain.builder.MakeBuilder
import com.olegpetrov.carservice.dto.CreateMakeDto
import com.olegpetrov.carservice.repository.MakeRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MakeService(private val makeRepository: MakeRepository, private val makeBuilder: MakeBuilder) {

    @Transactional(readOnly = true)
    fun getAllMakes(): List<String> {
        return makeRepository.findAll().map { it.name }
    }

    @Transactional
    fun createMake(createMakeDto: CreateMakeDto) {
        val make = makeBuilder.build(createMakeDto)
        makeRepository.save(make)
    }

    @Transactional(readOnly = true)
    fun findOneByName(name: String): Make? {
        return makeRepository.findMakeByName(name)
    }
}
