package com.example.kai.framework.datasource.cache.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.kai.framework.datasource.cache.model.ArticleCacheEntity

@Database(entities = [ArticleCacheEntity::class], version = 1)
abstract class ArticleDatabase: RoomDatabase() {

    abstract fun articleDao(): ArticleDao

    companion object {
        const val DATABASE_NAME = "ARTICLE_DB"
    }
}