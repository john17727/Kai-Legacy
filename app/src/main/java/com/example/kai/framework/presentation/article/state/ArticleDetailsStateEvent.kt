package com.example.kai.framework.presentation.article.state

import com.example.kai.business.domain.state.StateEvent

sealed class ArticleDetailsStateEvent: StateEvent {
    class GetArticleDetailsEvent(val url: String) : ArticleDetailsStateEvent() {
        override fun errorInfo(): String {
            return "Could not fetch details at this time"
        }

        override fun eventName(): String {
            return "GetArticleDetailsEvent"
        }

        override fun shouldDisplayProgressBar() = true

    }
}
