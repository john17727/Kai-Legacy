package com.example.kai.business.data.network.implementation

import com.example.kai.business.data.network.abstraction.ArticleNetworkDataSource
import com.example.kai.business.domain.model.ArticleResponse
import com.example.kai.framework.datasource.network.abstraction.ArticleNetworkService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ArticleNetworkDataSourceImpl
@Inject
constructor(
    private val articleNetworkService: ArticleNetworkService
): ArticleNetworkDataSource {
    override suspend fun getTopHeadlines(country: String, page: Int) = articleNetworkService.getTopHeadlines(country, page)
}