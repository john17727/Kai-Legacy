package com.example.kai.framework.datasource.network.service

import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ArticleAPIService {

    @GET("top-headlines")
    suspend fun getTopHeadlines(
        @Header("Authorization") token: String,
        @Query("country") country: String,
        @Query("page") page: Int
    )
}