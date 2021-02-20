package com.example.kai.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.example.kai.Kai
import com.example.kai.framework.datasource.cache.database.ArticleDatabase
import com.example.kai.framework.datasource.network.service.ArticleApiService
import com.example.kai.framework.datasource.network.service.ArticleDetailsApiService
import com.example.kai.framework.datasource.preferences.PreferenceKeys
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import retrofit2.Retrofit
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@FlowPreview
@Module
@InstallIn(SingletonComponent::class)
object ProductionModule {

    @Singleton
    @Provides
    fun provideArticleDB(app: Kai): ArticleDatabase {
        return Room.databaseBuilder(app, ArticleDatabase::class.java, ArticleDatabase.DATABASE_NAME)
            .fallbackToDestructiveMigration().build()
    }

    @Singleton
    @Provides
    fun provideArticleApiService(@NewsAPI retrofitBuilder: Retrofit.Builder): ArticleApiService {
        return retrofitBuilder.build().create(ArticleApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideArticleDetailsApiService(@ScrapperAPI retrofitBuilder: Retrofit.Builder): ArticleDetailsApiService {
        return retrofitBuilder.build().create(ArticleDetailsApiService::class.java)
    }

    @Singleton
    @Provides
    fun providesSharedPreferences(
        application: Kai
    ) : SharedPreferences {
        return application.getSharedPreferences(PreferenceKeys.NEWS_PREFERENCES, Context.MODE_PRIVATE)
    }
}