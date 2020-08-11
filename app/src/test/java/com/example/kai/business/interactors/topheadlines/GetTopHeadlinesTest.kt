package com.example.kai.business.interactors.topheadlines

import com.example.kai.business.data.cache.abstraction.ArticleCacheDataSource
import com.example.kai.business.data.network.abstraction.ArticleNetworkDataSource
import com.example.kai.business.domain.state.DataState
import com.example.kai.business.interactors.topheadlines.GetTopHeadlines.Companion.ARTICLES_SUCCESS
import com.example.kai.business.interactors.topheadlines.GetTopHeadlines.Companion.NO_MORE_ARTICLES
import com.example.kai.di.DependencyContainer
import com.example.kai.framework.presentation.topheadlines.state.TopHeadlinesStateEvent
import com.example.kai.framework.presentation.topheadlines.state.TopHeadlinesStateEvent.*
import com.example.kai.framework.presentation.topheadlines.state.TopHeadlinesViewState
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

/*
Test cases:
1. success_confirmHeadlinesRetrieved()
2. success_confirmNoMoreArticles()
3. fail_confirmNoResults() SETUP LATER
 */

@InternalCoroutinesApi
class GetTopHeadlinesTest {

    // system in test
    private var getTopHeadlines: GetTopHeadlines

    // dependencies
    private val dependencyContainer: DependencyContainer = DependencyContainer()
    private val articleCacheDataSource: ArticleCacheDataSource
    private val articleNetworkDataSource: ArticleNetworkDataSource

    init {
        dependencyContainer.build()
        articleCacheDataSource = dependencyContainer.articleCacheDataSource
        articleNetworkDataSource = dependencyContainer.articleNetworkDataSource
        getTopHeadlines = GetTopHeadlines(
            articleCacheDataSource,
            articleNetworkDataSource
        )
    }

    @Test
    fun success_confirmHeadlinesRetrieved() = runBlocking {

        getTopHeadlines = GetTopHeadlines(
            articleCacheDataSource,
            dependencyContainer.getFullNetworkDataSource()
        )

        getTopHeadlines.getTopHeadlines(
            "us",
            1,
            GetTopHeadlinesEvent()
        ).collect(object : FlowCollector<DataState<TopHeadlinesViewState>>{
            override suspend fun emit(value: DataState<TopHeadlinesViewState>) {
                assertEquals(
                    value.stateMessage?.response?.message,
                    ARTICLES_SUCCESS
                )
            }
        })
    }

    @Test
    fun success_confirmNoMoreArticles() = runBlocking {

        getTopHeadlines = GetTopHeadlines(
            articleCacheDataSource,
            dependencyContainer.getEmptyNetworkDataSource()
        )

        getTopHeadlines.getTopHeadlines(
            "us",
            1,
            GetTopHeadlinesEvent()
        ).collect(object : FlowCollector<DataState<TopHeadlinesViewState>>{
            override suspend fun emit(value: DataState<TopHeadlinesViewState>) {
                assertEquals(
                    value.stateMessage?.response?.message,
                    NO_MORE_ARTICLES
                )
            }
        })
    }
}