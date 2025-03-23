package com.olegpetrov.carservice.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(value = HttpStatus.NOT_FOUND)
class NotFoundException : RuntimeException {
    constructor() : super()
    constructor(id: Long) : super("Object with id $id not found")
}
