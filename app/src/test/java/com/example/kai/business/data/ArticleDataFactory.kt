package com.example.kai.business.data

import com.example.kai.business.domain.model.Article
import com.example.kai.business.domain.model.ArticleResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ArticleDataFactory(
    private val testClassLoader: ClassLoader
) {

    fun produceListOfArticles() : ArticleResponse {
        return Gson().fromJson(
            getArticlesFromFile("article_list.json"),
            object : TypeToken<ArticleResponse>(){}.type
        )
    }

    fun produceHashMapOfArticles(articleResponse: ArticleResponse): HashMap<String, Article> {
        val map = HashMap<String, Article>()
        for ((i, article) in articleResponse.articles.withIndex()) {
            map[i.toString()] = article
        }
        return map
    }

    fun getArticlesFromFile(filename: String) = testClassLoader.getResource(filename).readText()
}