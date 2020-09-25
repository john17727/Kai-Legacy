package com.example.kai.di

import com.example.kai.Kai
import com.example.kai.MainActivity
import com.example.kai.framework.presentation.topheadlines.TopHeadlinesFragment
import dagger.BindsInstance
import dagger.Component
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@FlowPreview
@Singleton
@Component(
    modules = [
        AppModule::class,
        ProductionModule::class,
        NewsViewModelModule::class,
        NewsFragmentFactoryModule::class
    ]
)
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance app: Kai): AppComponent
    }

    fun inject(mainActivity: MainActivity)

    fun inject(topHeadlinesFragment: TopHeadlinesFragment)
}