package com.example.kai.framework.presentation.topheadlines

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kai.R
import com.example.kai.framework.presentation.common.SpacingItemDecorator
import com.example.kai.framework.presentation.topheadlines.ArticleListAdapter.ArticleSelectedListener
import com.example.kai.util.Extensions.toLowerCase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_top_headlines.*
import kotlinx.android.synthetic.main.item_filters.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@FlowPreview
@ExperimentalCoroutinesApi
@AndroidEntryPoint
class TopHeadlinesFragment : Fragment(R.layout.fragment_top_headlines), ArticleSelectedListener {

    private lateinit var navController: NavController
    private lateinit var topHeadlinesAdapter: ArticleListAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager
    private val viewModel: TopHeadlinesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Need to initialize the adapter here because onViewCreated gets called every time
        // that this fragment is popped from the backstack resetting the adapter and clearing
        // the recycler's position.
        topHeadlinesAdapter = ArticleListAdapter(this)

        // Also make the call for the topHeadlines here for the same reason as above.
        getTopHeadlines("general")

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = view.findNavController()

        setupRecycler()
        setupObservers()

        topHeadlinesRefresh.setOnRefreshListener {
            getTopHeadlines(viewModel.getCategory())
        }

        category_chip_group.setOnCheckedChangeListener { _, checkedId ->
            val category = when (checkedId) {
                business.id -> business.text
                entertainment.id -> entertainment.text
                health.id -> health.text
                science.id -> science.text
                sports.id -> sports.text
                technology.id -> technology.text
                else -> general.text
            }
//            Toast.makeText(activity, category.toLowerCase(), Toast.LENGTH_SHORT).show()
            getTopHeadlines(category.toLowerCase().toString())
        }
    }

    private fun setupRecycler() {
        topHeadlinesRecycler.apply {

            linearLayoutManager = LinearLayoutManager(activity)
            layoutManager = linearLayoutManager

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
                    topHeadlinesAdapter.submitList(it.toList()) {
                        if (viewModel.getPage() == 1) {
                            linearLayoutManager.scrollToPosition(0)
                        }
                    }
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

    private fun getTopHeadlines(category: String) {
        viewModel.loadFirstPage(category)
    }

    override fun onArticleClick(articleUrl: String) {
        val action =
            TopHeadlinesFragmentDirections.actionTopHeadlinesFragmentToArticleFragment(articleUrl)
        navController.navigate(action)
    }
}