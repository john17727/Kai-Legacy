package com.example.kai.business.data.network.implementation

import com.example.kai.business.data.network.abstraction.ArticleNetworkDataSource
import com.example.kai.business.domain.model.ArticleDetailsResponse
import com.example.kai.framework.datasource.network.abstraction.ArticleNetworkService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ArticleNetworkDataSourceImpl
@Inject
constructor(
    private val articleNetworkService: ArticleNetworkService
): ArticleNetworkDataSource {
    override suspend fun getTopHeadlines(country: String, page: Int, category: String) = articleNetworkService.getTopHeadlines(country, page, category)

    override suspend fun getArticleDetails(url: String) = articleNetworkService.getArticleDetails(url)
}