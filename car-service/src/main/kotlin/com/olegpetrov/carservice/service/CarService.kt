package com.olegpetrov.carservice.service

import com.olegpetrov.carservice.domain.builder.CarBuilder
import com.olegpetrov.carservice.domain.options.CarIncludeOptions
import com.olegpetrov.carservice.domain.specification.CarSpecification
import com.olegpetrov.carservice.domain.specification.SortingUtils.Companion.getSort
import com.olegpetrov.carservice.dto.*
import com.olegpetrov.carservice.exception.BadRequestException
import com.olegpetrov.carservice.exception.NotFoundException
import com.olegpetrov.carservice.mapper.CarMapper
import com.olegpetrov.carservice.repository.CarRepository
import com.olegpetrov.carservice.repository.ManagerRepository
import com.olegpetrov.carservice.repository.ModelRepository
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CarService(
    private val carRepository: CarRepository,
    private val carMapper: CarMapper,
    private val carBuilder: CarBuilder,
    private val modelRepository: ModelRepository,
    private val managerRepository: ManagerRepository,
    private val s3Service: S3Service,
) {

    @Transactional(readOnly = true)
    fun searchCars(searchCarDto: SearchCarDto): PageDto<CarDto> {
        val sort = searchCarDto.sortBy?.let { getSort(it) } ?: Sort.unsorted()
        val pageable = PageRequest.of(
            searchCarDto.page - 1,
            searchCarDto.pageSize,
            sort
        )

        val carSpecification = CarSpecification.createSpecification(searchCarDto, CarIncludeOptions(false, false))
        val carIds = carRepository.findAll(carSpecification, pageable).content.map { it.id }
        // needed to avoid in memory paging
        val carIdsSpecification = CarSpecification.createSpecification(
            SearchCarDto(
                ids = carIds,
                page = searchCarDto.page,
                pageSize = searchCarDto.pageSize,
                sortBy = searchCarDto.sortBy
            )
        )
        val carsPage = carRepository.findAll(carIdsSpecification, pageable)

        return PageDto(carsPage, carMapper.toDtoList(carsPage.content));
    }

    @Transactional(readOnly = true)
    fun getCarById(id: Long): CarDto {
        val includeOptions = CarIncludeOptions(includeManager = true, includePhotos = true)
        val carSpecification = CarSpecification.createSpecification(SearchCarDto(id = id), includeOptions)
        val car = carRepository.findAll(carSpecification).firstOrNull()
            ?: throw NotFoundException(id)
        return carMapper.toDto(car, includeOptions)
    }

    @Transactional
    fun createCar(createCarDto: CreateCarDto) {
        val model =
            modelRepository.findByNameAndMake_Name(createCarDto.model, createCarDto.make)
                ?: throw BadRequestException(
                    "Model with name ${createCarDto.model} and make ${createCarDto.make} not found"
                )

        val manager =
            managerRepository.findByIdOrNull(createCarDto.managerId)
                ?: throw BadRequestException(
                    "Manager with id ${createCarDto.managerId} not found"
                )

        val photoUrls = s3Service.uploadFiles(createCarDto.photos)
        val car = carBuilder.buildCar(createCarDto, model, manager, photoUrls)
        carRepository.save(car)
    }

    @Transactional
    fun updateCar(id: Long, updateCarDto: UpdateCarDto) {
        val car = carRepository.findByIdOrNull(id) ?: throw NotFoundException(id)

        val model = if (updateCarDto.model != null && updateCarDto.make != null) {
            modelRepository.findByNameAndMake_Name(updateCarDto.model, updateCarDto.make)
                ?: throw BadRequestException("Model with name ${updateCarDto.model} and make ${updateCarDto.make} not found")
        } else {
            null
        }

        val manager = updateCarDto.managerId?.let {
            managerRepository.findByIdOrNull(it)
                ?: throw BadRequestException("Manager with id $it not found")
        }
        val photoUrls = updateCarDto.photos?.let {
             s3Service.uploadFiles(updateCarDto.photos)
        }
        carBuilder.updateCar(car, updateCarDto, model, manager, photoUrls)
    }
}
