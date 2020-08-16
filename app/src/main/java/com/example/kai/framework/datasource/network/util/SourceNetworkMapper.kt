package com.example.kai.framework.datasource.network.util

import com.example.kai.business.domain.model.Source
import com.example.kai.business.domain.util.EntityMapper
import com.example.kai.framework.datasource.network.model.SourceNetworkEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SourceNetworkMapper
@Inject
constructor(): EntityMapper<SourceNetworkEntity, Source> {
    override fun mapFromEntity(entity: SourceNetworkEntity): Source {
        return Source(
            entity.id,
            entity.name
        )
    }

    override fun mapToEntity(model: Source): SourceNetworkEntity {
        return SourceNetworkEntity(
            model.id,
            model.name
        )
    }
}