package com.example.newsinshorts.features.article.presentation.article_list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.newsinshorts.R
import com.example.newsinshorts.features.article.presentation.components.ArticleItem


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticleListScreen(
    viewModel: ArticleListViewModel = hiltViewModel(),
    onArticleClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier
            .fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.app_name)) },
                modifier = Modifier.fillMaxWidth()
            )
        }
    ) { paddingValues ->
        Box(
            modifier = modifier
                .padding(paddingValues)
                .padding(horizontal = 8.dp)
        ) {
            LazyColumn(modifier = modifier.fillMaxSize()) {
                items(viewModel.state.articlesList) { it ->
                    ArticleItem(
                        it,
                        onArticleClick = onArticleClick
                    )
                }
            }
            if (viewModel.state.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            viewModel.state.errorMessage?.let { error ->
                Text(
                    text = error.toString(),
                    textAlign = TextAlign.Center,
                    color = Color.Red,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}