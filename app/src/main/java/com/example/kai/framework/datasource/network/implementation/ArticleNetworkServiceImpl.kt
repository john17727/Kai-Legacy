package com.example.kai.framework.datasource.network.implementation

import com.example.kai.business.domain.model.ArticleResponse
import com.example.kai.framework.datasource.network.abstraction.ArticleNetworkService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ArticleNetworkServiceImpl
@Inject
constructor(): ArticleNetworkService {

    override suspend fun getTopHeadlines(country: String, Page: Int): ArticleResponse {
        TODO("Not yet implemented")
    }
}