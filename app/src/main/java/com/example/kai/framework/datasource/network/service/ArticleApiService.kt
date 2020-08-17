package com.example.kai.framework.datasource.network.service

import com.example.kai.framework.datasource.network.model.ArticleResponseEntity
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ArticleApiService {

    @GET("top-headlines")
    suspend fun getTopHeadlines(
        @Header("Authorization") token: String,
        @Query("country") country: String,
        @Query("page") page: Int
    ): ArticleResponseEntity
}