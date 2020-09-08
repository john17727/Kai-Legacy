package com.example.kai.di

import com.example.kai.Kai
import com.example.kai.MainActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

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
}