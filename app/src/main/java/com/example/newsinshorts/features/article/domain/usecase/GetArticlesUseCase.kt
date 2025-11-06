package com.example.newsinshorts.features.article.domain.usecase

import androidx.paging.PagingData
import com.example.newsinshorts.core.domain.NetworkResult
import com.example.newsinshorts.features.article.domain.model.Article
import com.example.newsinshorts.features.article.domain.repository.ArticlesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetArticlesUseCase @Inject constructor(
    private val repository: ArticlesRepository
) {
    operator fun invoke(): Flow<PagingData<Article>> {
        return repository.fetchArticles()
    }
}