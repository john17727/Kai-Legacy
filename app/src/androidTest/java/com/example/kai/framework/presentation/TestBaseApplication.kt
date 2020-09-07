package com.example.kai.framework.presentation

import com.example.kai.Kai
import com.example.kai.di.DaggerTestAppComponent

class TestBaseApplication : Kai() {

    override fun initAppComponent() {
        appComponent = DaggerTestAppComponent.factory().create(this)
    }
}