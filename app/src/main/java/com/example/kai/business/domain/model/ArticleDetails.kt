package com.example.kai.business.domain.model

data class ArticleDetails(
    val title: String,
    val text: String,
    val author: String,
    val images: List<String>,
    val topImage: String?,
    val url: String
)
