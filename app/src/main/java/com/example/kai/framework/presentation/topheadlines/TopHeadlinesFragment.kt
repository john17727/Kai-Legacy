package com.example.kai.framework.presentation.topheadlines

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kai.R
import com.example.kai.business.domain.util.DateUtil
import com.example.kai.framework.presentation.common.BaseNewsFragment
import com.example.kai.framework.presentation.common.SpacingItemDecorator
import com.example.kai.framework.presentation.topheadlines.ArticleListAdapter.ArticleSelectedListener
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
) : BaseNewsFragment(R.layout.fragment_top_headlines), ArticleSelectedListener {

    private lateinit var navController: NavController
    private lateinit var topHeadlinesAdapter: ArticleListAdapter
    private val viewModel: TopHeadlinesViewModel by viewModels {
        viewModelFactory
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = view.findNavController()

        setupRecycler()
        setupObservers()
        getTopHeadlines()

        topHeadlinesRefresh.setOnRefreshListener {
            getTopHeadlines()
        }
    }

    private fun setupRecycler() {
        topHeadlinesRecycler.apply {
            layoutManager = LinearLayoutManager(activity)

            addItemDecoration(SpacingItemDecorator(32))

            topHeadlinesAdapter = ArticleListAdapter(this@TopHeadlinesFragment)

            adapter = topHeadlinesAdapter
        }
    }

    private fun setupObservers() {
        viewModel.viewState.observe(viewLifecycleOwner, { viewState ->
            if (viewState != null) {
                viewState.articleList?.let {
                    topHeadlinesAdapter.submitList(it)
                }
            }
            topHeadlinesRefresh.isRefreshing = false
        })

        viewModel.stateMessage.observe(viewLifecycleOwner, { stateMessage ->
            stateMessage?.let {
                viewModel.clearStateMessage()
            }
        })
    }

    private fun getTopHeadlines() {
        viewModel.setStateEvent(TopHeadlinesStateEvent.GetTopHeadlinesEvent("us", 1))
    }

    override fun inject() {
        getAppComponent().inject(this)
    }

    override fun onArticleClick(articleUrl: String) {
        val action = TopHeadlinesFragmentDirections.actionTopHeadlinesFragmentToArticleFragment(articleUrl)
        navController.navigate(action)
    }
}