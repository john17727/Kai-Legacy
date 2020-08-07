package com.example.kai.framework.datasource.cache.abstraction

import com.example.kai.business.domain.model.Article

interface ArticleDaoService {

    suspend fun insertArticle(article: Article): Long

    suspend fun insertArticles(articles: List<Article>): LongArray

    suspend fun searchArticles(query: String, page: Int): List<Article>
}