package com.example.kai.framework.presentation.article

import com.example.kai.business.domain.model.ArticleDetails
import com.example.kai.business.domain.state.DataState
import com.example.kai.business.domain.state.StateEvent
import com.example.kai.business.interactors.articledetails.ArticleDetailsInteractors
import com.example.kai.framework.presentation.article.state.ArticleDetailsStateEvent.GetArticleDetailsEvent
import com.example.kai.framework.presentation.article.state.ArticleDetailsViewState
import com.example.kai.framework.presentation.common.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ExperimentalCoroutinesApi
@FlowPreview
@HiltViewModel
class ArticleViewModel
@Inject
constructor(private val articleDetailsInteractors: ArticleDetailsInteractors) : BaseViewModel<ArticleDetailsViewState>() {

    override fun handleNewData(data: ArticleDetailsViewState) {
        data.articleDetails?.let {
            setArticleDetails(it)
        }
    }

    override fun setStateEvent(stateEvent: StateEvent) {
        val job: Flow<DataState<ArticleDetailsViewState>?> = when (stateEvent) {
            is GetArticleDetailsEvent -> {
                articleDetailsInteractors.articleDetails.getArticleDetails(stateEvent.url, stateEvent)
            }
            else -> {
                emitInvalidStateEvent(stateEvent)
            }
        }
        launchJob(stateEvent, job)
    }

    override fun initNewViewState(): ArticleDetailsViewState {
        return ArticleDetailsViewState()
    }

    private fun setArticleDetails(article: ArticleDetails) {
        val update = getCurrentViewStateOrNew()

        update.articleDetails = article

        setViewState(update)
    }
}