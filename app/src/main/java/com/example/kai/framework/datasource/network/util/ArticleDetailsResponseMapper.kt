package com.example.kai.framework.datasource.network.util

import com.example.kai.business.domain.model.ArticleDetailsResponse
import com.example.kai.business.domain.util.EntityMapper
import com.example.kai.framework.datasource.network.model.ArticleDetailsResponseEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ArticleDetailsResponseMapper
@Inject
constructor(private val articleDetailsNetworkMapper: ArticleDetailsNetworkMapper): EntityMapper<ArticleDetailsResponseEntity, ArticleDetailsResponse> {

    override fun mapFromEntity(entity: ArticleDetailsResponseEntity): ArticleDetailsResponse {
        return ArticleDetailsResponse(
            articleDetailsNetworkMapper.mapFromEntity(entity.article)
        )
    }

    override fun mapToEntity(model: ArticleDetailsResponse): ArticleDetailsResponseEntity {
        return ArticleDetailsResponseEntity(
            articleDetailsNetworkMapper.mapToEntity(model.article)
        )
    }
}