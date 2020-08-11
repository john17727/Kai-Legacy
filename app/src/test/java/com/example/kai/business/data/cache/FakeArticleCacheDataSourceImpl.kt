package com.example.kai.business.data.cache

import com.example.kai.business.data.cache.abstraction.ArticleCacheDataSource
import com.example.kai.business.domain.model.Article

class FakeArticleCacheDataSourceImpl
constructor(
    private val articlesData: HashMap<String, Article>
): ArticleCacheDataSource{
    override suspend fun insertArticle(article: Article): Long {
        TODO("Not yet implemented")
    }

    override suspend fun insertArticles(articles: List<Article>): LongArray {
        TODO("Not yet implemented")
    }

    override suspend fun searchArticles(query: String, page: Int): List<Article> {
        TODO("Not yet implemented")
    }
}