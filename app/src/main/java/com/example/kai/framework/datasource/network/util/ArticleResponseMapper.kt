package com.example.kai.framework.datasource.network.util

import com.example.kai.business.domain.model.ArticleResponse
import com.example.kai.business.domain.util.EntityMapper
import com.example.kai.framework.datasource.network.model.ArticleResponseEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ArticleResponseMapper
@Inject
constructor(
    private val articleNetworkMapper: ArticleNetworkMapper
): EntityMapper<ArticleResponseEntity, ArticleResponse> {

    override fun mapFromEntity(entity: ArticleResponseEntity): ArticleResponse {
        return ArticleResponse(
            entity.status,
            entity.totalResults,
            articleNetworkMapper.mapFromEntityList(entity.articles)
        )
    }

    override fun mapToEntity(model: ArticleResponse): ArticleResponseEntity {
        return ArticleResponseEntity(
            model.status,
            model.totalResults,
            articleNetworkMapper.mapToEntityList(model.articles)
        )
    }
}