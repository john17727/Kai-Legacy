package com.example.kai

import android.app.Application
import com.example.kai.di.AppComponent
import com.example.kai.di.DaggerAppComponent

open class Kai: Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        initAppComponent()
    }

    open fun initAppComponent() {
        appComponent = DaggerAppComponent.factory().create(this)
    }
}