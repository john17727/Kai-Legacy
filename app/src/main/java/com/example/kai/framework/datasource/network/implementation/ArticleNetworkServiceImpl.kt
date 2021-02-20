package com.example.kai.framework.datasource.network.implementation

import com.example.kai.business.domain.model.ArticleDetailsResponse
import com.example.kai.business.domain.model.ArticleResponse
import com.example.kai.framework.datasource.network.abstraction.ArticleNetworkService
import com.example.kai.framework.datasource.network.service.ArticleApiService
import com.example.kai.framework.datasource.network.service.ArticleDetailsApiService
import com.example.kai.framework.datasource.network.util.ArticleDetailsResponseMapper
import com.example.kai.framework.datasource.network.util.ArticleResponseMapper
import com.example.kai.util.SCRAPPER_KEY
import com.example.kai.util.TOKEN
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ArticleNetworkServiceImpl
@Inject
constructor(
    private val articleApiService: ArticleApiService,
    private val articleResponseMapper: ArticleResponseMapper,
    private val articleDetailsApiService: ArticleDetailsApiService,
    private val articleDetailsResponseMapper: ArticleDetailsResponseMapper
) : ArticleNetworkService {

    override suspend fun getTopHeadlines(
        country: String,
        page: Int,
        category: String
    ): ArticleResponse {
        return articleResponseMapper.mapFromEntity(
            articleApiService.getTopHeadlines(
                TOKEN,
                country,
                page,
                category
            )
        )
    }

    override suspend fun getArticleDetails(url: String): ArticleDetailsResponse {
        return articleDetailsResponseMapper.mapFromEntity(
            articleDetailsApiService.getArticleDetails(
                SCRAPPER_KEY,
                url
            )
        )
    }
}