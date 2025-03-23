package com.olegpetrov.carservice.mapper

interface BaseMapper<Entity, Dto> {
    fun toDto(entity: Entity): Dto

    fun toDtoList(entities: List<Entity>): List<Dto> = entities.map { toDto(it) }
}
