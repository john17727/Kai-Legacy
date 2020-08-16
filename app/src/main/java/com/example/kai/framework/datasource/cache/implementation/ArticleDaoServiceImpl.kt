package com.example.kai.framework.datasource.cache.implementation

import com.example.kai.business.domain.model.Article
import com.example.kai.framework.datasource.cache.abstraction.ArticleDaoService
import com.example.kai.framework.datasource.cache.database.ArticleDao
import com.example.kai.framework.datasource.cache.util.ArticleCacheMapper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ArticleDaoServiceImpl
@Inject
constructor(
    private val articleDao: ArticleDao,
    private val cacheMapper: ArticleCacheMapper
): ArticleDaoService {
    override suspend fun insertArticle(article: Article): Long {
        return articleDao.insertArticle(cacheMapper.mapToEntity(article))
    }

    override suspend fun insertArticles(articles: List<Article>): LongArray {
        return articleDao.insertArticles(cacheMapper.mapToEntityList(articles))
    }

    override suspend fun searchArticles(query: String, page: Int): List<Article> {
        // Might delete this later
        return cacheMapper.mapFromEntityList(articleDao.getArticles())
    }

    override suspend fun getArticles(): List<Article> {
        return cacheMapper.mapFromEntityList(articleDao.getArticles())
    }

}