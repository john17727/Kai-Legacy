package com.example.kai.framework.datasource.network.implementation

import com.example.kai.business.domain.model.ArticleResponse
import com.example.kai.framework.datasource.network.abstraction.ArticleNetworkService
import com.example.kai.framework.datasource.network.service.ArticleApiService
import com.example.kai.framework.datasource.network.util.ArticleResponseMapper
import com.example.kai.util.TOKEN
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ArticleNetworkServiceImpl
@Inject
constructor(
    private val articleApiService: ArticleApiService,
    private val articleResponseMapper: ArticleResponseMapper
) : ArticleNetworkService {

    override suspend fun getTopHeadlines(country: String, page: Int, category: String): ArticleResponse {
        return articleResponseMapper.mapFromEntity(
            articleApiService.getTopHeadlines(
                TOKEN,
                country,
                page,
                category
            )
        )
    }
}