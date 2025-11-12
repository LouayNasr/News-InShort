package com.example.newsinshorts.features.article.presentation.article_details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsinshorts.NewsDestinationArgs
import com.example.newsinshorts.core.domain.NetworkResult
import com.example.newsinshorts.core.presentation.toUiText
import com.example.newsinshorts.features.article.domain.usecase.GetArticleDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticleDetailsViewModel @Inject constructor(
    private val getArticleDetailsUseCase: GetArticleDetailsUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    val articleId: Int = checkNotNull(savedStateHandle[NewsDestinationArgs.ARTICLE_ID_ARG]).toString().toInt()
    var state by mutableStateOf(ArticleDetailsState())
        private set

    init {
        fetchArticleDetails(articleId)
    }

    fun fetchArticleDetails(id: Int) {
        viewModelScope.launch {
            when (val result = getArticleDetailsUseCase(id)) {
                is NetworkResult.Success -> {
                    state = state.copy(
                        article = result.data,
                        isLoading = false,
                        errorMessage = null
                    )
                }

                is NetworkResult.Error -> {
                    state = state.copy(
                        article = null,
                        isLoading = false,
                        errorMessage = result.error.toUiText()
                    )
                }
            }
        }
    }
}