package com.example.kai.business.domain.util

interface EntityMapper<Entity, Model> {

    fun mapFromEntity(entity: Entity): Model

    fun mapToEntity(model: Model): Entity
}