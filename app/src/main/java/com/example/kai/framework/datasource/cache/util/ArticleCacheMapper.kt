package com.example.kai.framework.datasource.cache.util

import com.example.kai.business.domain.model.Article
import com.example.kai.business.domain.util.DateUtil
import com.example.kai.business.domain.util.EntityMapper
import com.example.kai.framework.datasource.cache.model.ArticleCacheEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ArticleCacheMapper
@Inject
constructor(
    private val dateUtil: DateUtil,
    private val sourceCacheMapper: SourceCacheMapper
): EntityMapper<ArticleCacheEntity, Article> {

    fun mapFromEntityList(entities: List<ArticleCacheEntity>): List<Article> {
        val list = ArrayList<Article>()
        for (entity in entities) {
            list.add(mapFromEntity(entity))
        }
        return list
    }

    fun mapToEntityList(models: List<Article>): List<ArticleCacheEntity> {
        val list = ArrayList<ArticleCacheEntity>()
        for (model in models) {
            list.add(mapToEntity(model))
        }
        return list
    }

    override fun mapFromEntity(entity: ArticleCacheEntity): Article {
        return Article(
            source = sourceCacheMapper.mapFromEntity(entity.source),
            author = entity.author,
            title = entity.title,
            description = entity.description,
            url = entity.url,
            urlToImage = entity.urlToImage,
            publishedAt = entity.publishedAt,
            timeSincePublished = dateUtil.getPublishTimeSpan(entity.publishedAt),
            content = entity.content
        )
    }

    override fun mapToEntity(model: Article): ArticleCacheEntity {
        return ArticleCacheEntity(
            articleId = 0,
            source = sourceCacheMapper.mapToEntity(model.source),
            author = model.author,
            title = model.title,
            description = model.description,
            url = model.url,
            urlToImage = model.urlToImage,
            publishedAt = model.publishedAt,
            content = model.content
        )
    }
}