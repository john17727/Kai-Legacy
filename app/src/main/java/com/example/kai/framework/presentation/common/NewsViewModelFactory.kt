package com.example.kai.framework.presentation.common

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.kai.business.interactors.topheadlines.TopHeadlinesInteractors
import com.example.kai.framework.presentation.topheadlines.TopHeadlinesViewModel
import java.lang.IllegalArgumentException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsViewModelFactory
@Inject
constructor(
    private val topHeadlinesInteractors: TopHeadlinesInteractors,
    private val editor: SharedPreferences.Editor,
    private val sharedPreferences: SharedPreferences
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when(modelClass) {
            TopHeadlinesViewModel::class.java -> {
                TopHeadlinesViewModel(
                    topHeadlinesInteractors = topHeadlinesInteractors,
                    editor = editor,
                    sharedPreferences = sharedPreferences
                ) as T
            }
            else -> {
                throw IllegalArgumentException("unknown model class $modelClass")
            }
        }
    }

}