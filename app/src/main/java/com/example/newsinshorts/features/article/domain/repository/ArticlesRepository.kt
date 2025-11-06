package com.example.newsinshorts.features.article.domain.repository

import androidx.paging.PagingData
import com.example.newsinshorts.core.domain.NetworkResult
import com.example.newsinshorts.features.article.domain.model.Article
import kotlinx.coroutines.flow.Flow


interface ArticlesRepository {
    fun fetchArticles(): Flow<PagingData<Article>>
    suspend fun fetchArticleDetails(id: Int): NetworkResult<Article>
}