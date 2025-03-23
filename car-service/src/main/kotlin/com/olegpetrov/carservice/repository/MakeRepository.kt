package com.olegpetrov.carservice.repository

import com.olegpetrov.carservice.domain.Make
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MakeRepository : JpaRepository<Make, Long> {

    fun findMakeByName(name: String) : Make?
}
