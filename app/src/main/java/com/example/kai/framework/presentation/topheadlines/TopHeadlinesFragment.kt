package com.example.kai.framework.presentation.topheadlines

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kai.R
import com.example.kai.business.domain.util.DateUtil
import com.example.kai.framework.presentation.common.BaseNewsFragment
import com.example.kai.framework.presentation.common.SpacingItemDecorator
import com.example.kai.framework.presentation.topheadlines.ArticleListAdapter.ArticleSelectedListener
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Need to initialize the adapter here because onViewCreated gets called every time
        // that this fragment is popped from the backstack resetting the adapter and clearing
        // the recycler's position.
        topHeadlinesAdapter = ArticleListAdapter(this)

        // Also make the call for the topHeadlines here for the same reason as above.
        getTopHeadlines()

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = view.findNavController()

        setupRecycler()
        setupObservers()

        topHeadlinesRefresh.setOnRefreshListener {
            getTopHeadlines()
        }
    }

    private fun setupRecycler() {
        topHeadlinesRecycler.apply {
            layoutManager = LinearLayoutManager(activity)

            addItemDecoration(SpacingItemDecorator(32))

            adapter = topHeadlinesAdapter
        }

        topHeadlinesRecycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val lastPosition = layoutManager.findLastVisibleItemPosition()
                if (lastPosition == topHeadlinesAdapter.itemCount.minus(1)) {
                    viewModel.nextPage()
                }
            }
        })
    }

    private fun setupObservers() {
        viewModel.viewState.observe(viewLifecycleOwner, { viewState ->
            viewState.articleList?.let {
                if (it.isNotEmpty()) {
                    // toList() is used here to create a new list since submitlist
                    // won't update the recyclerview if the list has same reference.
                    topHeadlinesAdapter.submitList(it.toList())
                }
            }
        })

        viewModel.stateMessage.observe(viewLifecycleOwner, { stateMessage ->
            stateMessage?.let {
                viewModel.clearStateMessage()
            }
        })

        viewModel.shouldDisplayProgressBar.observe(viewLifecycleOwner, { displayProgressbar ->
            topHeadlinesRefresh.isRefreshing = displayProgressbar
        })
    }

    private fun getTopHeadlines() {
        viewModel.loadFirstPage()
    }

    override fun inject() {
        getAppComponent().inject(this)
    }

    override fun onArticleClick(articleUrl: String) {
        val action =
            TopHeadlinesFragmentDirections.actionTopHeadlinesFragmentToArticleFragment(articleUrl)
        navController.navigate(action)
    }
}