package com.example.kai.business.data.cache.abstraction

import com.example.kai.business.domain.model.Article

interface ArticleCacheDataSource {

    suspend fun insertArticle(article: Article): Long

    suspend fun insertArticles(articles: List<Article>): LongArray

    suspend fun searchArticles(query: String, page: Int): List<Article>

    suspend fun getArticles(): List<Article>
}