package com.example.kai.framework.presentation.topheadlines.state

import com.example.kai.business.domain.state.StateEvent

sealed class TopHeadlinesStateEvent: StateEvent {

    class GetTopHeadlinesEvent(val country: String, val page: Int, val category: String) : TopHeadlinesStateEvent() {
        override fun errorInfo(): String {
            return "Could not fetch news at this time"
        }

        override fun eventName(): String {
            return "GetTopHeadlinesEvent"
        }

        override fun shouldDisplayProgressBar() = true
    }
}