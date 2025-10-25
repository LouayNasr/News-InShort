package com.example.newsinshorts.features.article.domain.usecase

import com.example.newsinshorts.core.domain.NetworkResult
import com.example.newsinshorts.features.article.domain.model.Article
import com.example.newsinshorts.features.article.domain.repository.ArticlesRepository
import javax.inject.Inject


class GetArticlesUseCase @Inject constructor(
    private val repository: ArticlesRepository
) {
    suspend operator fun invoke(): NetworkResult<List<Article>> {
        return repository.fetchArticles()
    }
}