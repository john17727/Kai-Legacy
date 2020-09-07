package com.example.kai.framework

import androidx.test.core.app.ApplicationProvider
import com.example.kai.framework.presentation.TestBaseApplication

abstract class BaseTest {

    val application: TestBaseApplication = ApplicationProvider.getApplicationContext()

    abstract fun injectTest()
}