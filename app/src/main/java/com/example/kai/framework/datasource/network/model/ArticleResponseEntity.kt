package com.example.kai.framework.datasource.network.model

data class ArticleResponseEntity(
    val status: String,
    val totalResults: Int,
    val articles: List<ArticleNetworkEntity>
)