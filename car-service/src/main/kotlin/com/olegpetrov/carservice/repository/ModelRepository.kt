package com.olegpetrov.carservice.repository

import com.olegpetrov.carservice.domain.Model
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ModelRepository : JpaRepository<Model, Long> {

    fun findAllByMake_Name(makeName: String): List<Model>
    fun findByNameAndMake_Name(name: String, makeName: String): Model?
}
