package com.example.kai.business.data.network.abstraction

import com.example.kai.business.domain.model.ArticleResponse
import com.example.kai.framework.datasource.network.model.ArticleResponseEntity

interface ArticleNetworkDataSource {

    suspend fun getTopHeadlines(country: String, page: Int, category: String): ArticleResponse

}