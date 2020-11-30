package com.example.kai.framework.presentation.topheadlines.state

import android.os.Parcelable
import com.example.kai.business.domain.model.Article
import com.example.kai.business.domain.state.ViewState
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TopHeadlinesViewState(

    var articleList: ArrayList<Article>? = null,
    var page: Int? = null,
    var category: String = "general",
    var numArticles: Int? = null,
    var areArticlesExhausted: Boolean = false
): Parcelable, ViewState