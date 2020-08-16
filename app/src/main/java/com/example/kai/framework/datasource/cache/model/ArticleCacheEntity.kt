package com.example.kai.framework.datasource.cache.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Articles")
data class ArticleCacheEntity(
    @PrimaryKey(autoGenerate = true)
    val articleId: Int,
    @Embedded val source: SourceCacheEntity,
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: String,
    val content: String
)