package com.example.kai.framework.presentation.common

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import androidx.lifecycle.ViewModelProvider
import com.example.kai.business.domain.util.DateUtil
import com.example.kai.framework.presentation.topheadlines.TopHeadlinesFragment
import javax.inject.Inject

class NewsFragmentFactory
@Inject
constructor(
    private val viewModelFactory: ViewModelProvider.Factory,
    private val dateUtil: DateUtil
): FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when (className) {
            TopHeadlinesFragment::class.java.name -> {
                TopHeadlinesFragment(
                    viewModelFactory,
                    dateUtil
                )
            }
            else -> {
                super.instantiate(classLoader, className)
            }
        }
    }
}