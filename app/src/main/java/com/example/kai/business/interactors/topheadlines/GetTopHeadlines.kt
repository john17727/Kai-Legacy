package com.example.kai.business.interactors.topheadlines

import com.example.kai.business.data.cache.abstraction.ArticleCacheDataSource
import com.example.kai.business.data.network.ApiResponseHandler
import com.example.kai.business.data.network.abstraction.ArticleNetworkDataSource
import com.example.kai.business.data.util.safeApiCall
import com.example.kai.business.domain.model.Article
import com.example.kai.business.domain.model.ArticleResponse
import com.example.kai.business.domain.state.*
import com.example.kai.framework.presentation.topheadlines.state.TopHeadlinesViewState
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetTopHeadlines(
    private val articleCacheDataSource: ArticleCacheDataSource,
    private val articleNetworkDataSource: ArticleNetworkDataSource
) {

    val articleList: ArrayList<Article> = arrayListOf()

    fun getTopHeadlines(country: String, page: Int, category: String, stateEvent: StateEvent): Flow<DataState<TopHeadlinesViewState>> = flow {
        var updatedPage = page
        if (page <= 0) {
            updatedPage = 1
        }

        if (page == 1) articleList.clear() // Quick fix for list appending on refresh. Might want to look for new implementation of list appending

        val apiResult = safeApiCall(IO) {
            articleNetworkDataSource.getTopHeadlines(country, page, category)
        }
//        Log.d("TopHeadlines", "getTopHeadlines: $apiResult")

        val response = object : ApiResponseHandler<TopHeadlinesViewState, ArticleResponse>(
            response = apiResult,
            stateEvent = stateEvent
        ) {
            override suspend fun handleSuccess(resultObj: ArticleResponse): DataState<TopHeadlinesViewState> {

                var message: String = ARTICLES_SUCCESS

                if (resultObj.articles.isEmpty()) {
                    message = NO_MORE_ARTICLES
                }

                articleList.addAll(resultObj.articles)

                return DataState.data(
                    response = Response(
                        message = message,
                        uiComponentType = UIComponentType.None,
                        messageType = MessageType.Success
                    ),
                    data = TopHeadlinesViewState(
                        articleList = articleList,
                        page = updatedPage,
                        numArticles = resultObj.totalResults
                    ),
                    stateEvent = stateEvent
                )
            }
        }.getResult()

        emit(response)
    }

    companion object {
        const val NO_MORE_ARTICLES = "No more articles"
        const val ARTICLES_SUCCESS = "Successfully retrieved articles"
    }
}