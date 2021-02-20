package com.example.kai.framework.datasource.network.model

data class ArticleDetailsNetworkEntity(
    val title: String,
    val text: String,
    val author: String,
    val images: List<String>,
    val image: String,
    val url: String,
    val html: String
)
