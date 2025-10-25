package com.example.newsinshorts.features.article.presentation.article_details

import com.example.newsinshorts.core.presentation.UiText
import com.example.newsinshorts.features.article.domain.model.Article

data class ArticleDetailsState(
    val article: Article? = null,
    val isLoading: Boolean = true,
    val errorMessage: UiText? = null
)
