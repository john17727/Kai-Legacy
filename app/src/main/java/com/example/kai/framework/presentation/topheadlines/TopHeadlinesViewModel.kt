package com.example.kai.framework.presentation.topheadlines

import android.content.SharedPreferences
import com.example.kai.business.domain.model.Article
import com.example.kai.business.domain.state.DataState
import com.example.kai.business.domain.state.StateEvent
import com.example.kai.business.interactors.topheadlines.TopHeadlinesInteractors
import com.example.kai.framework.presentation.common.BaseViewModel
import com.example.kai.framework.presentation.topheadlines.state.TopHeadlinesStateEvent
import com.example.kai.framework.presentation.topheadlines.state.TopHeadlinesStateEvent.GetTopHeadlinesEvent
import com.example.kai.framework.presentation.topheadlines.state.TopHeadlinesViewState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow

@ExperimentalCoroutinesApi
@FlowPreview
class TopHeadlinesViewModel
constructor(
    private val topHeadlinesInteractors: TopHeadlinesInteractors,
    private val editor: SharedPreferences.Editor,
    private val sharedPreferences: SharedPreferences
): BaseViewModel<TopHeadlinesViewState>(){

    override fun handleNewData(data: TopHeadlinesViewState) {
        data.let { viewState ->
            viewState.articleList?.let { articleList ->
                setHeadlinesListData(articleList)
            }
        }
    }

    override fun setStateEvent(stateEvent: StateEvent) {
        val job: Flow<DataState<TopHeadlinesViewState>?> = when (stateEvent) {
            is GetTopHeadlinesEvent -> {
                topHeadlinesInteractors.getTopHeadlines.getTopHeadlines(
                    country = stateEvent.country,
                    page = stateEvent.page,
                    stateEvent = stateEvent
                )
            }
            else -> {
                emitInvalidStateEvent(stateEvent)
            }
        }
        launchJob(stateEvent, job)
    }

    override fun initNewViewState(): TopHeadlinesViewState {
        return TopHeadlinesViewState()
    }

    fun getPage() = getCurrentViewStateOrNew().page ?: 1

    fun getHeadlinesListSize() = getCurrentViewStateOrNew().articleList?.size ?: 0

    fun getTotalHeadLines() = getCurrentViewStateOrNew().numArticles ?: 0

    // for debugging
    fun getActiveJobs() = dataChannelManager.getActiveJobs()

    fun isPaginationExhausted() = getHeadlinesListSize() >= getTotalHeadLines()


    private fun setHeadlinesListData(headlines: ArrayList<Article>) {
        val update = getCurrentViewStateOrNew()
        update.articleList = headlines
        setViewState(update)
    }

    fun setArticlesExhausted(isExhausted: Boolean) {
        val update = getCurrentViewStateOrNew()
        update.areArticlesExhausted = isExhausted
        setViewState(update)
    }

    fun resetPage() {
        val update = getCurrentViewStateOrNew()
        update.page = 1
        setViewState(update)
    }

    fun incrementPageNumber() {
        val update = getCurrentViewStateOrNew()
        val page = update.page?: 1
        update.page = page.plus(1)
        setViewState(update)
    }

    fun loadFirstPage() {
        setArticlesExhausted(false)
        resetPage()
        setStateEvent(GetTopHeadlinesEvent("us", 1))
    }

    fun nextPage() {
        if (!isPaginationExhausted()) {
            incrementPageNumber()
            setStateEvent(GetTopHeadlinesEvent("us", 1))
        }
    }

}