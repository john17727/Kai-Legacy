package com.example.kai.framework.datasource.cache.util

import com.example.kai.business.domain.model.Article
import com.example.kai.business.domain.util.DateUtil
import com.example.kai.business.domain.util.EntityMapper
import com.example.kai.framework.datasource.cache.model.ArticleEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ArticleCacheMapper
@Inject
constructor(
    private val dateUtil: DateUtil,
    private val sourceCacheMapper: SourceCacheMapper
): EntityMapper<ArticleEntity, Article> {

    fun mapFromEntityList(entities: List<ArticleEntity>): List<Article> {
        val list = ArrayList<Article>()
        for (entity in entities) {
            list.add(mapFromEntity(entity))
        }
        return list
    }

    fun mapToEntityList(models: List<Article>): List<ArticleEntity> {
        val list = ArrayList<ArticleEntity>()
        for (model in models) {
            list.add(mapToEntity(model))
        }
        return list
    }

    override fun mapFromEntity(entity: ArticleEntity): Article {
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

    override fun mapToEntity(model: Article): ArticleEntity {
        return ArticleEntity(
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