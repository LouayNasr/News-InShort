package com.example.newsinshorts.features.article.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSourceFactory
import com.example.newsinshorts.core.domain.DataError
import com.example.newsinshorts.core.domain.NetworkResult
import com.example.newsinshorts.core.domain.safeApiCall
import com.example.newsinshorts.features.article.data.ArticlePagingSource
import com.example.newsinshorts.features.article.data.mapper.toArticle
import com.example.newsinshorts.features.article.data.remote.ArticleApiService
import com.example.newsinshorts.features.article.domain.model.Article
import com.example.newsinshorts.features.article.domain.repository.ArticlesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ArticlesRepositoryImpl @Inject constructor(
    private val articleApiService: ArticleApiService
) : ArticlesRepository {

    override fun fetchArticles(): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                ArticlePagingSource(articleApiService)
            }
        ).flow
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