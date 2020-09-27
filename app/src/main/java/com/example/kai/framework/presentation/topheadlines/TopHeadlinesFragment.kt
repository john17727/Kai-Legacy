package com.example.kai.framework.presentation.topheadlines

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kai.R
import com.example.kai.business.domain.util.DateUtil
import com.example.kai.framework.presentation.common.BaseNewsFragment
import com.example.kai.framework.presentation.common.SpacingItemDecorator
import com.example.kai.framework.presentation.topheadlines.state.TopHeadlinesStateEvent
import kotlinx.android.synthetic.main.fragment_top_headlines.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@FlowPreview
@ExperimentalCoroutinesApi
class TopHeadlinesFragment
constructor(
    private val viewModelFactory: ViewModelProvider.Factory,
    private val dateUtil: DateUtil
) : BaseNewsFragment(R.layout.fragment_top_headlines) {

    lateinit var topHeadlinesAdapter: ArticleListAdapter
    private val viewModel: TopHeadlinesViewModel by viewModels {
        viewModelFactory
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecycler()
        setupObservers()

        viewModel.setStateEvent(TopHeadlinesStateEvent.GetTopHeadlinesEvent("us", 1))
    }

    private fun setupRecycler() {
        topHeadlinesRecycler.apply {
            layoutManager = LinearLayoutManager(activity)

            addItemDecoration(SpacingItemDecorator(32))

            topHeadlinesAdapter = ArticleListAdapter()

            adapter = topHeadlinesAdapter
        }
    }

    private fun setupObservers() {
        viewModel.viewState.observe(viewLifecycleOwner, { viewState ->
            Log.d("TopHeadlines", "setupObservers: $viewState")
            if (viewState != null) {
                viewState.articleList?.let {
                    topHeadlinesAdapter.submitList(it)
                }
            }
        })
    }

    override fun inject() {
        getAppComponent().inject(this)
    }
}