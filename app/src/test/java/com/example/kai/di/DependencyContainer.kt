package com.example.kai.di

import com.example.kai.business.data.cache.FakeArticleCacheDataSourceImpl
import com.example.kai.business.data.cache.abstraction.ArticleCacheDataSource
import com.example.kai.business.data.network.FakeArticleNetworkDataSourceImpl
import com.example.kai.business.data.network.abstraction.ArticleNetworkDataSource
import com.example.kai.business.domain.model.Article
import com.example.kai.business.domain.model.Source

class DependencyContainer {

    lateinit var articleCacheDataSource: ArticleCacheDataSource
    lateinit var articleNetworkDataSource: ArticleNetworkDataSource

    fun build() {
        articleCacheDataSource = FakeArticleCacheDataSourceImpl(
            articlesData = HashMap()
        )
        articleNetworkDataSource = FakeArticleNetworkDataSourceImpl(
            createEmptyArticleHashMap()
        )
    }

    fun getFullNetworkDataSource(): ArticleNetworkDataSource {
        return FakeArticleNetworkDataSourceImpl(
            createArticleHashMap()
        )
    }

    fun getEmptyNetworkDataSource(): ArticleNetworkDataSource {
        return FakeArticleNetworkDataSourceImpl(
            createEmptyArticleHashMap()
        )
    }

    private fun createEmptyArticleHashMap(): HashMap<String, Article> {
        return HashMap()
    }

    private fun createArticleHashMap(): HashMap<String, Article> {
        val articlesData = HashMap<String, Article>()
        for (i in 0..20) {
            articlesData[i.toString()] = Article(
                Source(
                    "$i",
                    "name $i"
                ),
                "author $i",
                "title $i",
                "desc $i",
                "url $i",
                "urlToImage $i",
                "publishedAt $i",
                "content $i"
            )
        }
        return articlesData
    }
}