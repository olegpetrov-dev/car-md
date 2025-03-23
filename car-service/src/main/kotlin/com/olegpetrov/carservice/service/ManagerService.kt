package com.olegpetrov.carservice.service

import com.olegpetrov.carservice.domain.builder.ManagerBuilder
import com.olegpetrov.carservice.dto.ManagerDto
import com.olegpetrov.carservice.dto.UpsertManagerDto
import com.olegpetrov.carservice.mapper.ManagerMapper
import com.olegpetrov.carservice.repository.ManagerRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ManagerService(
    private val managerRepository: ManagerRepository,
    private val managerMapper: ManagerMapper,
    private val managerBuilder: ManagerBuilder
) {

    @Transactional(readOnly = true)
    fun getAllManagers(): List<ManagerDto> {
        return managerRepository.findAll().map { managerMapper.toDto(it) }
    }

    @Transactional
    fun createManager(createManagerDto: UpsertManagerDto) {
        val manager = managerBuilder.build(name = createManagerDto.name, phone = createManagerDto.phone)
        managerRepository.save(manager)
    }

}
