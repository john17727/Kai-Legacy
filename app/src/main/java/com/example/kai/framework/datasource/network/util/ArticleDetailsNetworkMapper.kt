package com.example.kai.framework.datasource.network.util

import com.example.kai.business.domain.model.ArticleDetails
import com.example.kai.business.domain.util.EntityMapper
import com.example.kai.framework.datasource.network.model.ArticleDetailsNetworkEntity
import javax.inject.Singleton

@Singleton
class ArticleDetailsNetworkMapper : EntityMapper<ArticleDetailsNetworkEntity, ArticleDetails> {

    override fun mapFromEntity(entity: ArticleDetailsNetworkEntity): ArticleDetails {
        return ArticleDetails(
            entity.title,
            entity.text,
            entity.author,
            entity.images,
            entity.image,
            entity.url,
            entity.html
        )
    }

    override fun mapToEntity(model: ArticleDetails): ArticleDetailsNetworkEntity {
        return ArticleDetailsNetworkEntity(
            model.title,
            model.text,
            model.author,
            model.images,
            model.topImage?: "",
            model.url,
            model.html
        )
    }
}