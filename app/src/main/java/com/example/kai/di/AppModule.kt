package com.example.kai.di

import android.content.Context
import android.content.SharedPreferences
import com.example.kai.Kai
import com.example.kai.business.data.cache.abstraction.ArticleCacheDataSource
import com.example.kai.business.data.cache.implementation.ArticleCacheDataSourceImpl
import com.example.kai.business.data.network.abstraction.ArticleNetworkDataSource
import com.example.kai.business.data.network.implementation.ArticleNetworkDataSourceImpl
import com.example.kai.business.domain.util.DateUtil
import com.example.kai.business.interactors.articledetails.ArticleDetailsInteractors
import com.example.kai.business.interactors.articledetails.GetArticleDetails
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
import com.example.kai.framework.datasource.network.service.ArticleDetailsApiService
import com.example.kai.framework.datasource.network.util.*
import com.example.kai.util.NEWS_BASE_URL
import com.example.kai.util.SCRAPPER_BASE_URL
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.format.DateTimeFormatter
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext kai: Context): Kai {
        return kai as Kai
    }

    @Singleton
    @Provides
    fun provideGsonBuilder(): Gson {
        return GsonBuilder().create()
    }

    @Singleton
    @Provides
    @NewsAPI
    fun provideNewsRetrofitBuilder(gson: Gson): Retrofit.Builder {
        return Retrofit.Builder().baseUrl(NEWS_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
    }

    @Singleton
    @Provides
    @ScrapperAPI
    fun provideScrapperRetrofitBuilder(gson: Gson): Retrofit.Builder {
        return Retrofit.Builder().baseUrl(SCRAPPER_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
    }

    // date format: yyyy-MM-dd'T'HH:mm:ss'Z'
    @Singleton
    @Provides
    fun provideDateTimeFormatter(): DateTimeFormatter {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
    }

    @Singleton
    @Provides
    fun provideDateUtil(dateTimeFormatter: DateTimeFormatter): DateUtil {
        return DateUtil(dateTimeFormatter)
    }

    @Singleton
    @Provides
    fun provideSharedPrefsEditor(sharedPreferences: SharedPreferences): SharedPreferences.Editor {
        return sharedPreferences.edit()
    }

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

    @Singleton
    @Provides
    fun provideSourceCacheMapper(): SourceCacheMapper {
        return SourceCacheMapper()
    }

    @Singleton
    @Provides
    fun provideArticleNetworkMapper(
        dateUtil: DateUtil,
        sourceNetworkMapper: SourceNetworkMapper
    ): ArticleNetworkMapper {
        return ArticleNetworkMapper(dateUtil, sourceNetworkMapper)
    }

    @Singleton
    @Provides
    fun provideArticleResponseMapper(articleNetworkMapper: ArticleNetworkMapper): ArticleResponseMapper {
        return ArticleResponseMapper(articleNetworkMapper)
    }

    @Singleton
    @Provides
    fun provideSourceNetworkMapper(): SourceNetworkMapper {
        return SourceNetworkMapper()
    }

    @Singleton
    @Provides
    fun provideArticleDetailsNetworkMapper(): ArticleDetailsNetworkMapper {
        return ArticleDetailsNetworkMapper()
    }

    @Singleton
    @Provides
    fun provideArticleDetailsResponseMapper(articleDetailsNetworkMapper: ArticleDetailsNetworkMapper): ArticleDetailsResponseMapper {
        return ArticleDetailsResponseMapper(articleDetailsNetworkMapper)
    }

    @Singleton
    @Provides
    fun provideArticleDaoService(
        articleDao: ArticleDao,
        articleCacheMapper: ArticleCacheMapper
    ): ArticleDaoService {
        return ArticleDaoServiceImpl(articleDao, articleCacheMapper)
    }

    @Singleton
    @Provides
    fun provideArticleCacheDataSource(articleDaoService: ArticleDaoService): ArticleCacheDataSource {
        return ArticleCacheDataSourceImpl(articleDaoService)
    }

    @Singleton
    @Provides
    fun provideArticleNetworkService(
        articleApiService: ArticleApiService,
        articleResponseMapper: ArticleResponseMapper,
        articleDetailsApiService: ArticleDetailsApiService,
        articleDetailsResponseMapper: ArticleDetailsResponseMapper
    ): ArticleNetworkService {
        return ArticleNetworkServiceImpl(articleApiService, articleResponseMapper, articleDetailsApiService, articleDetailsResponseMapper)
    }

    @Singleton
    @Provides
    fun provideArticleNetworkDataSource(articleNetworkService: ArticleNetworkService): ArticleNetworkDataSource {
        return ArticleNetworkDataSourceImpl(articleNetworkService)
    }

    @Singleton
    @Provides
    fun provideGetTopHeadlines(
        articleCacheDataSource: ArticleCacheDataSource,
        articleNetworkDataSource: ArticleNetworkDataSource
    ): GetTopHeadlines {
        return GetTopHeadlines(articleCacheDataSource, articleNetworkDataSource)
    }

    @Singleton
    @Provides
    fun provideTopHeadlinesInteractors(getTopHeadlines: GetTopHeadlines): TopHeadlinesInteractors {
        return TopHeadlinesInteractors(getTopHeadlines)
    }

    @Singleton
    @Provides
    fun provideGetArticleDetails(
        articleNetworkDataSource: ArticleNetworkDataSource
    ): GetArticleDetails {
        return GetArticleDetails(articleNetworkDataSource)
    }

    @Singleton
    @Provides
    fun provideArticleDetailsInteractors(getArticleDetails: GetArticleDetails): ArticleDetailsInteractors {
        return ArticleDetailsInteractors(getArticleDetails)
    }
}