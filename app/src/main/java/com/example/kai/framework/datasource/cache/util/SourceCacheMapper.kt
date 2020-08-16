package com.example.kai.framework.datasource.cache.util

import com.example.kai.business.domain.model.Source
import com.example.kai.business.domain.util.EntityMapper
import com.example.kai.framework.datasource.cache.model.SourceCacheEntity
import javax.inject.Inject

class SourceCacheMapper
@Inject
constructor(): EntityMapper<SourceCacheEntity, Source> {
    override fun mapFromEntity(entity: SourceCacheEntity): Source {
        return Source(
            entity.id,
            entity.name
        )
    }

    override fun mapToEntity(model: Source): SourceCacheEntity {
        return SourceCacheEntity(
            model.id?:"",
            model.name
        )
    }

}