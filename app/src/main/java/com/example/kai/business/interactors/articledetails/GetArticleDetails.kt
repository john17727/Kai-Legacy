package com.example.kai.business.interactors.articledetails

import com.example.kai.business.data.network.ApiResponseHandler
import com.example.kai.business.data.network.abstraction.ArticleNetworkDataSource
import com.example.kai.business.data.util.safeApiCall
import com.example.kai.business.domain.model.ArticleDetailsResponse
import com.example.kai.business.domain.state.*
import com.example.kai.framework.presentation.article.state.ArticleDetailsViewState
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetArticleDetails(
    private val articleNetworkDataSource: ArticleNetworkDataSource
) {
    fun getArticleDetails(url: String, stateEvent: StateEvent): Flow<DataState<ArticleDetailsViewState>> = flow {
        val apiResult = safeApiCall(IO) {
            articleNetworkDataSource.getArticleDetails(url)
        }

        val response = object : ApiResponseHandler<ArticleDetailsViewState, ArticleDetailsResponse>(
            response = apiResult,
            stateEvent = stateEvent
        ) {
            override suspend fun handleSuccess(resultObj: ArticleDetailsResponse): DataState<ArticleDetailsViewState> {
                return DataState.data(
                    response = Response(
                        message = DETAILS_SUCCESS,
                        uiComponentType = UIComponentType.None,
                        messageType = MessageType.Success
                    ),
                    data = ArticleDetailsViewState(
                        articleDetails = resultObj.article
                    ),
                    stateEvent = stateEvent
                )
            }

        }.getResult()

        emit(response)
    }

    companion object {
        const val DETAILS_SUCCESS = "Successfully retrieved article details"
    }
}