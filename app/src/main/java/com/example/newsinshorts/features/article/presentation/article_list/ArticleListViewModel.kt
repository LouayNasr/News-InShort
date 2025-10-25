package com.example.newsinshorts.features.article.presentation.article_list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsinshorts.core.domain.NetworkResult
import com.example.newsinshorts.core.presentation.toUiText
import com.example.newsinshorts.features.article.domain.usecase.GetArticlesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticleListViewModel @Inject constructor(
    private val getArticlesUseCase: GetArticlesUseCase
) : ViewModel() {

    var state by mutableStateOf(ArticleListState())
        private set

    init {
        fetchArticles()
    }

    private fun fetchArticles() {
        viewModelScope.launch {
            when (val result = getArticlesUseCase()) {
                is NetworkResult.Success -> {
                    state = state.copy(
                        articlesList = result.data,
                        isLoading = false,
                        errorMessage = null
                    )
                }

                is NetworkResult.Error -> {
                    state = state.copy(
                        articlesList = emptyList(),
                        isLoading = false,
                        errorMessage = result.error.toUiText()
                    )
                }
            }
        }
    }
}
