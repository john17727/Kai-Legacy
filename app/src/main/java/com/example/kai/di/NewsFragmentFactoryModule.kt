package com.example.kai.di

import androidx.fragment.app.FragmentFactory
import androidx.lifecycle.ViewModelProvider
import com.example.kai.business.domain.util.DateUtil
import com.example.kai.framework.presentation.common.NewsFragmentFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object NewsFragmentFactoryModule {

    @JvmStatic
    @Singleton
    @Provides
    fun provideNewsFragmentFactory(
        viewModelFactory: ViewModelProvider.Factory,
        dateUtil: DateUtil
    ) : FragmentFactory {
        return NewsFragmentFactory(
            viewModelFactory,
            dateUtil
        )
    }
}