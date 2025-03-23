package com.olegpetrov.carservice.dto

import org.springframework.data.domain.Page

data class PageDto<Dto>(
    val content: List<Dto>,
    val page: Int,
    val size: Int,
    val totalElements: Long,
    val totalPages: Int
) {
    constructor(page: Page<*>, content: List<Dto>) : this(
        content = content,
        page = page.number + 1, // Convert zero-based page index to one-based
        size = page.size,
        totalElements = page.totalElements,
        totalPages = page.totalPages
    )
}
