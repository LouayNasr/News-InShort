package com.example.newsinshorts.features.article.presentation.article_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.newsinshorts.features.article.domain.model.Article
import com.example.newsinshorts.features.article.domain.usecase.GetArticlesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class ArticleListViewModel @Inject constructor(
    getArticlesUseCase: GetArticlesUseCase
) : ViewModel() {

    val articles: Flow<PagingData<Article>> = getArticlesUseCase().cachedIn(viewModelScope)
}