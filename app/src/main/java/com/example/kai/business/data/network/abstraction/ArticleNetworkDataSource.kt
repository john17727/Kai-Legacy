package com.example.kai.business.data.network.abstraction

import com.example.kai.business.domain.model.ArticleResponse

interface ArticleNetworkDataSource {

    suspend fun getTopHeadlines(country: String, page: Int): ArticleResponse
}