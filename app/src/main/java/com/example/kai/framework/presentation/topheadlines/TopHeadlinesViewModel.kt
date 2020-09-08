package com.example.kai.framework.presentation.topheadlines

import android.content.SharedPreferences
import com.example.kai.business.domain.state.StateEvent
import com.example.kai.business.interactors.topheadlines.TopHeadlinesInteractors
import com.example.kai.framework.presentation.common.BaseViewModel
import com.example.kai.framework.presentation.topheadlines.state.TopHeadlinesViewState

class TopHeadlinesViewModel
constructor(
    private val topHeadlinesInteractors: TopHeadlinesInteractors,
    private val editor: SharedPreferences.Editor,
    private val sharedPreferences: SharedPreferences
): BaseViewModel<TopHeadlinesViewState>(){

    override fun handleNewData(data: TopHeadlinesViewState) {

    }

    override fun setStateEvent(stateEvent: StateEvent) {
    }

    override fun initNewViewState(): TopHeadlinesViewState {
        return TopHeadlinesViewState()
    }

}