package com.example.kai.framework.presentation.article

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.kai.R
import com.example.kai.framework.presentation.article.state.ArticleDetailsStateEvent.GetArticleDetailsEvent
import com.example.kai.framework.presentation.util.ViewExtensions.load
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_article.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@FlowPreview
@ExperimentalCoroutinesApi
@AndroidEntryPoint
class ArticleFragment : Fragment(R.layout.fragment_article) {

    private val viewModel: ArticleViewModel by viewModels()

    val args: ArticleFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        articleWebView.loadUrl(args.articleUrl)

        subscribeObservers()

        getArticleDetails(args.articleUrl)
    }

    private fun subscribeObservers() {
        viewModel.viewState.observe(viewLifecycleOwner, { viewState ->
            viewState.articleDetails?.let { article ->
                Log.d("Details", "Top Image: ${article.topImage}")

                article.topImage?.let { image ->
                    mainImage.load(image)
                }
                detailsTitle.text = article.title
                detailsContent.text = article.text
            }
        })
    }

    private fun getArticleDetails(url: String) {
        viewModel.setStateEvent(GetArticleDetailsEvent(url))
    }
}