package com.example.kai.framework.datasource.cache.util

import com.example.kai.business.domain.model.Source
import com.example.kai.business.domain.util.EntityMapper
import com.example.kai.framework.datasource.cache.model.SourceEntity
import javax.inject.Inject

class SourceCacheMapper
@Inject
constructor(): EntityMapper<SourceEntity, Source> {
    override fun mapFromEntity(entity: SourceEntity): Source {
        return Source(
            entity.id,
            entity.name
        )
    }

    override fun mapToEntity(model: Source): SourceEntity {
        return SourceEntity(
            model.id?:"",
            model.name
        )
    }

}