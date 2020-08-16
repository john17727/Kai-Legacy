package com.example.kai.framework.datasource.cache.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.kai.framework.datasource.cache.model.ArticleCacheEntity

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticle(article: ArticleCacheEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticles(articles: List<ArticleCacheEntity>): LongArray

    @Query("SELECT * FROM Articles")
    suspend fun getArticles(): List<ArticleCacheEntity>

}