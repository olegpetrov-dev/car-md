package com.olegpetrov.carservice.domain.builder

import com.olegpetrov.carservice.domain.Manager
import org.springframework.stereotype.Component

@Component
class ManagerBuilder {
    fun build(name: String, phone: String): Manager {
        return Manager(name = name, phone = phone)
    }
}
