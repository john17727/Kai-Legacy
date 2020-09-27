package com.example.kai.framework.datasource.network.util

import com.example.kai.business.domain.model.Article
import com.example.kai.business.domain.util.DateUtil
import com.example.kai.business.domain.util.EntityMapper
import com.example.kai.framework.datasource.network.model.ArticleNetworkEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ArticleNetworkMapper
@Inject
constructor(
    private val dateUtil: DateUtil,
    private val sourceNetworkMapper: SourceNetworkMapper
): EntityMapper<ArticleNetworkEntity, Article> {

    fun mapFromEntityList(entities: List<ArticleNetworkEntity>): List<Article> {
        val list = ArrayList<Article>()
        for (entity in entities) {
            list.add(mapFromEntity(entity))
        }
        return list
    }

    fun mapToEntityList(models: List<Article>): List<ArticleNetworkEntity> {
        val list = ArrayList<ArticleNetworkEntity>()
        for (model in models) {
            list.add(mapToEntity(model))
        }
        return list
    }

    override fun mapFromEntity(entity: ArticleNetworkEntity): Article {
        return Article(
            source = sourceNetworkMapper.mapFromEntity(entity.source),
            author = entity.author?:"",
            title = removeSourceTagFromTitle(entity.title),
            description = entity.description?:"",
            url = entity.url,
            urlToImage = entity.urlToImage?:"",
            publishedAt = entity.publishedAt,
            timeSincePublished = dateUtil.getPublishTimeSpan(entity.publishedAt),
            content = entity.content?:""
        )
    }

    private fun removeSourceTagFromTitle(title: String): String {
        if (title.contains(" - ")) {
            val hyphenTail = """\s-\s.*""".toRegex()
            return hyphenTail.replace(title, "")
        }
        return title
    }

    override fun mapToEntity(model: Article): ArticleNetworkEntity {
        return ArticleNetworkEntity(
            source = sourceNetworkMapper.mapToEntity(model.source),
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