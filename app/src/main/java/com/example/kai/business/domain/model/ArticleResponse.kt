package com.example.kai.business.domain.model

data class ArticleResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<Article>
) {
}