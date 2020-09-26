package com.example.kai.framework.datasource.network.model

import com.example.kai.business.domain.model.Source

data class ArticleNetworkEntity(
    val source: SourceNetworkEntity,
    val author: String?,
    val title: String,
    val description: String?,
    val url: String,
    val urlToImage: String?,
    val publishedAt: String,
    val content: String?
) {
}