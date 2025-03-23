package com.olegpetrov.carservice.repository

import com.olegpetrov.carservice.domain.Manager
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ManagerRepository : JpaRepository<Manager, Long> {

}
