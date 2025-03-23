package com.olegpetrov.carservice.repository

import com.olegpetrov.carservice.domain.Car
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository

@Repository
interface CarRepository : JpaRepository<Car, Long>, JpaSpecificationExecutor<Car> {

//    @EntityGraph(attributePaths = ["model"])
//    override fun findAll(spec: Specification<Car>?, pageable: Pageable): Page<Car>

//    @EntityGraph(attributePaths = ["manager", "photos"], type = EntityGraph.EntityGraphType.LOAD)
//    override fun findById(id: Long): Optional<Car>
}
