package com.example.kai.di

import androidx.room.Room
import com.example.kai.Kai
import com.example.kai.framework.datasource.cache.database.ArticleDatabase
import com.example.kai.framework.datasource.network.service.ArticleApiService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
object ProductionModule {

    @JvmStatic
    @Singleton
    @Provides
    fun provideArticleDB(app: Kai): ArticleDatabase {
        return Room.databaseBuilder(app, ArticleDatabase::class.java, ArticleDatabase.DATABASE_NAME)
            .fallbackToDestructiveMigration().build()
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideArticleApiService(retrofitBuilder: Retrofit.Builder): ArticleApiService {
        return retrofitBuilder.build().create(ArticleApiService::class.java)
    }
}