package com.olegpetrov.carservice.service

import com.olegpetrov.carservice.domain.builder.ModelBuilder
import com.olegpetrov.carservice.dto.CreateModelDto
import com.olegpetrov.carservice.repository.ModelRepository
import org.apache.coyote.BadRequestException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ModelService(
    private val modelRepository: ModelRepository,
    private val makeService: MakeService,
    private val modelBuilder: ModelBuilder
) {

    @Transactional(readOnly = true)
    fun getModelsForMake(make: String): List<String> {
        return modelRepository.findAllByMake_Name(make).map { it.name }
    }

    @Transactional
    fun createModel(createModelDto: CreateModelDto) {
        val make = makeService.findOneByName(createModelDto.makeName)
            ?: throw BadRequestException("Make with name ${createModelDto.makeName} not found")
        val model = modelBuilder.build(createModelDto.name, make)
        modelRepository.save(model)
    }
}
