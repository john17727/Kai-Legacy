package com.example.kai.business.data.cache.implementation

import com.example.kai.business.data.cache.abstraction.ArticleCacheDataSource
import com.example.kai.business.domain.model.Article
import com.example.kai.framework.datasource.cache.abstraction.ArticleDaoService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ArticleCacheDataSourceImpl
@Inject
constructor(
    private val articleDaoService: ArticleDaoService
): ArticleCacheDataSource {
    override suspend fun insertArticle(article: Article) = articleDaoService.insertArticle(article)

    override suspend fun insertArticles(articles: List<Article>) = articleDaoService.insertArticles(articles)

    override suspend fun searchArticles(
        query: String,
        page: Int
    ) = articleDaoService.searchArticles(query, page)

    override suspend fun getArticles() = articleDaoService.getArticles()
}