package com.olegpetrov.carservice.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
class BadRequestException : RuntimeException {
    constructor() : super()
    constructor(message: String) : super(message)
}
