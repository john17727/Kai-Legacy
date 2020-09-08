package com.example.kai.framework.presentation.topheadlines

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.kai.R
import com.example.kai.business.domain.util.DateUtil
import com.example.kai.framework.presentation.common.BaseNewsFragment

class TopHeadlinesFragment
constructor(
    private val viewModelFactory: ViewModelProvider.Factory,
    private val dateUtil: DateUtil
) : BaseNewsFragment(R.layout.fragment_top_headlines) {

    val viewModel: TopHeadlinesViewModel by viewModels {
        viewModelFactory
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun inject() {
        TODO("Not yet implemented")
    }
}