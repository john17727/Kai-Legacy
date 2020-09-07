package com.example.kai.business

import androidx.test.core.app.ApplicationProvider
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.kai.di.TestAppComponent
import com.example.kai.framework.datasource.network.service.ArticleApiService
import com.example.kai.framework.presentation.TestBaseApplication
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@RunWith(AndroidJUnit4ClassRunner::class)
class TempTest {
    val application: TestBaseApplication =
        ApplicationProvider.getApplicationContext() as TestBaseApplication

    @Inject
    lateinit var articleApiService: ArticleApiService

    init {
        (application.appComponent as TestAppComponent).inject(this)
    }

    @Test
    fun someRandomTest() {
        assert(::articleApiService.isInitialized)
    }
}