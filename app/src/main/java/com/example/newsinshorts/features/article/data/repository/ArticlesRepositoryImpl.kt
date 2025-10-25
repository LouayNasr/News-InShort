package com.example.newsinshorts.features.article.data.repository

import com.example.newsinshorts.core.domain.DataError
import com.example.newsinshorts.core.domain.NetworkResult
import com.example.newsinshorts.core.domain.safeApiCall
import com.example.newsinshorts.features.article.data.mapper.toArticle
import com.example.newsinshorts.features.article.data.remote.ArticleApiService
import com.example.newsinshorts.features.article.domain.model.Article
import com.example.newsinshorts.features.article.domain.repository.ArticlesRepository
import javax.inject.Inject

class ArticlesRepositoryImpl @Inject constructor(
    private val articleApiService: ArticleApiService
) : ArticlesRepository {
    override suspend fun fetchArticles(): NetworkResult<List<Article>> {
        return when (val response = safeApiCall {
            articleApiService.fetchNewsList()
        }) {
            is NetworkResult.Success -> {
                try {
                    val articles = response.data.result.map { it.toArticle() }
                    NetworkResult.Success(articles)
                } catch (e: Exception) {
                    NetworkResult.Error(DataError.Remote.SERIALIZATION)
                }
            }

            is NetworkResult.Error -> response
        }
    }

    override suspend fun fetchArticleDetails(id: Int): NetworkResult<Article> {
        return when (val response = safeApiCall {
            articleApiService.fetchArticleDetails(id)
        }) {
            is NetworkResult.Success -> {
                try {
                    val articleDetails = response.data.let { it.toArticle() }
                    NetworkResult.Success(articleDetails)
                } catch (e: Exception) {
                    NetworkResult.Error(DataError.Remote.SERIALIZATION)
                }
            }

            is NetworkResult.Error -> response
        }
    }
}