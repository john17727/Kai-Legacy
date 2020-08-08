package com.example.kai.business.interactors.topheadlines

import com.example.kai.business.data.cache.abstraction.ArticleCacheDataSource
import com.example.kai.business.data.network.abstraction.ArticleNetworkDataSource
import com.example.kai.business.domain.state.DataState
import com.example.kai.business.domain.state.StateEvent
import com.example.kai.framework.presentation.topheadlines.state.TopHeadlinesViewState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetTopHeadlines(
    private val articleCacheDataSource: ArticleCacheDataSource,
    private val articleNetworkDataSource: ArticleNetworkDataSource
) {

    fun getTopHeadlines(country: String, stateEvent: StateEvent): Flow<DataState<TopHeadlinesViewState>> = flow {

    }
}