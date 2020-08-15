package com.example.kai.framework.datasource.cache.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.kai.business.domain.model.Source

@Entity(tableName = "Articles")
data class ArticleEntity(
    @PrimaryKey(autoGenerate = true)
    val articleId: Int,
    @Embedded val source: SourceEntity,
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: String,
    val content: String
)