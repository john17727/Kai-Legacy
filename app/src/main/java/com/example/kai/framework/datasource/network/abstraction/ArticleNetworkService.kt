package com.example.kai.framework.datasource.network.abstraction

import com.example.kai.business.domain.model.ArticleDetailsResponse
import com.example.kai.business.domain.model.ArticleResponse

interface ArticleNetworkService {

    suspend fun getTopHeadlines(country: String, page: Int, category: String): ArticleResponse

    suspend fun getArticleDetails(url: String): ArticleDetailsResponse
}