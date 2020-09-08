package com.example.kai.di

import android.content.SharedPreferences
import androidx.lifecycle.ViewModelProvider
import com.example.kai.business.interactors.topheadlines.TopHeadlinesInteractors
import com.example.kai.framework.presentation.common.NewsViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object NewsViewModelModule {

    @Singleton
    @JvmStatic
    @Provides
    fun provideNewsViewModelFactory(
        topHeadlinesInteractors: TopHeadlinesInteractors,
        editor: SharedPreferences.Editor,
        sharedPreferences: SharedPreferences
    ): ViewModelProvider.Factory {
        return NewsViewModelFactory(
            topHeadlinesInteractors = topHeadlinesInteractors,
            editor = editor,
            sharedPreferences = sharedPreferences
        )
    }
}