package com.example.kai.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.example.kai.Kai
import com.example.kai.framework.datasource.cache.database.ArticleDatabase
import com.example.kai.framework.datasource.network.service.ArticleApiService
import com.example.kai.framework.datasource.preferences.PreferenceKeys
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import retrofit2.Retrofit
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@FlowPreview
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

    @JvmStatic
    @Singleton
    @Provides
    fun providesSharedPreferences(
        application: Kai
    ) : SharedPreferences {
        return application.getSharedPreferences(PreferenceKeys.NEWS_PREFERENCES, Context.MODE_PRIVATE)
    }
}