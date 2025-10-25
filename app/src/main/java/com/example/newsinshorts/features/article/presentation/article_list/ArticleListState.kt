package com.example.newsinshorts.features.article.presentation.article_list

import com.example.newsinshorts.core.presentation.UiText
import com.example.newsinshorts.features.article.domain.model.Article

data class ArticleListState(
    val articlesList: List<Article> = emptyList(),
    val isLoading: Boolean = true,
    val errorMessage: UiText? = null
)
