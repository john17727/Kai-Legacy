package com.example.kai.framework.datasource.network.service

import com.example.kai.framework.datasource.network.model.ArticleDetailsResponseEntity
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ArticleDetailsApiService {

    @GET("extract")
    suspend fun getArticleDetails(
        @Header("x-rapidapi-key") key: String,
        @Query("url") url: String
    ): ArticleDetailsResponseEntity
}