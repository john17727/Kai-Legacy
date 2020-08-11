package com.example.kai.business.data.network

import com.example.kai.business.data.network.abstraction.ArticleNetworkDataSource
import com.example.kai.business.domain.model.Article
import com.example.kai.business.domain.model.ArticleResponse

class FakeArticleNetworkDataSourceImpl
constructor(
    private val articlesData: HashMap<String, Article>
): ArticleNetworkDataSource {
    override suspend fun getTopHeadlines(country: String, page: Int): ArticleResponse {
        return ArticleResponse(
            status = "ok",
            totalResults = 20,
            articles = ArrayList(articlesData.values)
        )
    }
}