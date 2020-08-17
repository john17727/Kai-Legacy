package com.example.kai.di

import android.content.SharedPreferences
import com.example.kai.business.data.cache.abstraction.ArticleCacheDataSource
import com.example.kai.business.data.cache.implementation.ArticleCacheDataSourceImpl
import com.example.kai.business.data.network.abstraction.ArticleNetworkDataSource
import com.example.kai.business.data.network.implementation.ArticleNetworkDataSourceImpl
import com.example.kai.business.domain.util.DateUtil
import com.example.kai.business.interactors.topheadlines.GetTopHeadlines
import com.example.kai.business.interactors.topheadlines.TopHeadlinesInteractors
import com.example.kai.framework.datasource.cache.abstraction.ArticleDaoService
import com.example.kai.framework.datasource.cache.database.ArticleDao
import com.example.kai.framework.datasource.cache.database.ArticleDatabase
import com.example.kai.framework.datasource.cache.implementation.ArticleDaoServiceImpl
import com.example.kai.framework.datasource.cache.util.ArticleCacheMapper
import com.example.kai.framework.datasource.cache.util.SourceCacheMapper
import com.example.kai.framework.datasource.network.abstraction.ArticleNetworkService
import com.example.kai.framework.datasource.network.implementation.ArticleNetworkServiceImpl
import com.example.kai.framework.datasource.network.service.ArticleApiService
import com.example.kai.framework.datasource.network.util.ArticleNetworkMapper
import com.example.kai.framework.datasource.network.util.ArticleResponseMapper
import com.example.kai.framework.datasource.network.util.SourceNetworkMapper
import com.example.kai.util.BASE_URL
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.format.DateTimeFormatter
import javax.inject.Singleton

@Module
object AppModule {

    @JvmStatic
    @Singleton
    @Provides
    fun provideGsonBuilder(): Gson {
        return GsonBuilder().create()
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideRetrofitBuilder(gson: Gson): Retrofit.Builder {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
    }

    // date format: yyyy-MM-dd'T'HH:mm:ss'Z'
    @JvmStatic
    @Singleton
    @Provides
    fun provideDateTimeFormatter(): DateTimeFormatter {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideDateUtil(dateTimeFormatter: DateTimeFormatter): DateUtil {
        return DateUtil(dateTimeFormatter)
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideSharedPrefsEditor(sharedPreferences: SharedPreferences): SharedPreferences.Editor {
        return sharedPreferences.edit()
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideArticleDao(articleDatabase: ArticleDatabase): ArticleDao {
        return articleDatabase.articleDao()
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideArticleCacheMapper(
        dateUtil: DateUtil,
        sourceCacheMapper: SourceCacheMapper
    ): ArticleCacheMapper {
        return ArticleCacheMapper(dateUtil, sourceCacheMapper)
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideSourceCacheMapper(): SourceCacheMapper {
        return SourceCacheMapper()
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideArticleNetworkMapper(
        dateUtil: DateUtil,
        sourceNetworkMapper: SourceNetworkMapper
    ): ArticleNetworkMapper {
        return ArticleNetworkMapper(dateUtil, sourceNetworkMapper)
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideArticleResponseMapper(articleNetworkMapper: ArticleNetworkMapper): ArticleResponseMapper {
        return ArticleResponseMapper(articleNetworkMapper)
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideSourceNetworkMapper(): SourceNetworkMapper {
        return SourceNetworkMapper()
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideArticleDaoService(
        articleDao: ArticleDao,
        articleCacheMapper: ArticleCacheMapper
    ): ArticleDaoService {
        return ArticleDaoServiceImpl(articleDao, articleCacheMapper)
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideArticleCacheDataSource(articleDaoService: ArticleDaoService): ArticleCacheDataSource {
        return ArticleCacheDataSourceImpl(articleDaoService)
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideArticleNetworkService(
        articleApiService: ArticleApiService,
        articleResponseMapper: ArticleResponseMapper
    ): ArticleNetworkService {
        return ArticleNetworkServiceImpl(articleApiService, articleResponseMapper)
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideArticleNetworkDataSource(articleNetworkService: ArticleNetworkService): ArticleNetworkDataSource {
        return ArticleNetworkDataSourceImpl(articleNetworkService)
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideGetTopHeadlines(
        articleCacheDataSource: ArticleCacheDataSource,
        articleNetworkDataSource: ArticleNetworkDataSource
    ): GetTopHeadlines {
        return GetTopHeadlines(articleCacheDataSource, articleNetworkDataSource)
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideTopHeadlinesInteractors(getTopHeadlines: GetTopHeadlines): TopHeadlinesInteractors {
        return TopHeadlinesInteractors(getTopHeadlines)
    }
}