package com.example.kai.di

import androidx.room.Room
import com.example.kai.framework.datasource.cache.database.ArticleDatabase
import com.example.kai.framework.datasource.network.service.ArticleApiService
import com.example.kai.framework.presentation.TestBaseApplication
import com.example.kai.util.BASE_URL
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
object TestModule {

    @JvmStatic
    @Singleton
    @Provides
    fun provideArticleDB(app: TestBaseApplication): ArticleDatabase {
        return Room.inMemoryDatabaseBuilder(app, ArticleDatabase::class.java)
            .fallbackToDestructiveMigration().build()
    }

//    @JvmStatic
//    @Singleton
//    @Provides
//    fun provideGsonBuilder(): Gson {
//        return GsonBuilder().create()
//    }
//
//    @JvmStatic
//    @Singleton
//    @Provides
//    fun provideRetrofitBuilder(gson: Gson): Retrofit.Builder {
//        return Retrofit.Builder().baseUrl(BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create(gson))
//    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideArticleApiService(retrofitBuilder: Retrofit.Builder): ArticleApiService {
        return retrofitBuilder.build().create(ArticleApiService::class.java)
    }
}