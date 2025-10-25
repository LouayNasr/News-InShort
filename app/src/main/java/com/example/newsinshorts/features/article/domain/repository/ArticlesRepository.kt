package com.example.newsinshorts.features.article.domain.repository

import com.example.newsinshorts.core.domain.NetworkResult
import com.example.newsinshorts.features.article.domain.model.Article


interface ArticlesRepository {
    suspend fun fetchArticles(): NetworkResult<List<Article>>
    suspend fun fetchArticleDetails(id: Int): NetworkResult<Article>
}