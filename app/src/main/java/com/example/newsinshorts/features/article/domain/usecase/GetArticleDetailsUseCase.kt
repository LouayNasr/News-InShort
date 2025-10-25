package com.example.newsinshorts.features.article.domain.usecase

import com.example.newsinshorts.core.domain.NetworkResult
import com.example.newsinshorts.features.article.domain.model.Article
import com.example.newsinshorts.features.article.domain.repository.ArticlesRepository
import javax.inject.Inject

class GetArticleDetailsUseCase @Inject constructor(
    private val repository: ArticlesRepository
) {
    suspend operator fun invoke(articleId: Int): NetworkResult<Article> {
        return repository.fetchArticleDetails(articleId)
    }
}