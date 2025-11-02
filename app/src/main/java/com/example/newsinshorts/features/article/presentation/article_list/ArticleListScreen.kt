package com.example.newsinshorts.features.article.presentation.article_list

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.newsinshorts.R
import com.example.newsinshorts.core.presentation.UiText
import com.example.newsinshorts.features.article.domain.model.Article
import com.example.newsinshorts.features.article.presentation.components.ArticleItem
import java.time.LocalDateTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticleListScreen(
    viewModel: ArticleListViewModel = hiltViewModel(),
    onArticleClick: (Int) -> Unit,
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.app_name)) },
                modifier = Modifier.fillMaxWidth()
            )
        }
    ) { paddingValues ->
        val state = viewModel.state

        ArticleListScreenContent(
            state = state,
            onArticleClick = onArticleClick,
            modifier = Modifier.padding(paddingValues)
        )
    }
}

@Composable
fun ArticleListScreenContent(
    state: ArticleListState,
    onArticleClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier
            .padding(horizontal = 8.dp)
    ) {
        LazyColumn(modifier = modifier.fillMaxSize()) {
            items(state.articlesList) { it ->
                ArticleItem(
                    it,
                    onArticleClick = onArticleClick
                )
            }
        }
        if (state.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }
        state.errorMessage?.let { error ->
            Text(
                text = error.toString(),
                textAlign = TextAlign.Center,
                color = Color.Red,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

@Preview
@Composable
fun ArticlesListScreenContentLoadingPreview() {
    Surface {
        ArticleListScreenContent(
            state = ArticleListState(emptyList(), true, null),
            onArticleClick = {}
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun ArticlesListScreenContentSuccessPreview() {
    Surface {
        ArticleListScreenContent(
            state = ArticleListState(
                listOf(
                    Article(
                        1,
                        "title",
                        emptyList(),
                        "",
                        "https://camo.githubusercontent.com/3cae61090608b8cbd681f5825ca5ac76af8d8d3ee12024926d51c5480aef5d6c/68747470733a2f2f796176757a63656c696b65722e6769746875622e696f2f73616d706c652d696d616765732f696d6167652d313032312e6a7067",
                        "",
                        LocalDateTime.now()
                    ),
                    Article(1, "another title", emptyList(), "", "", "", LocalDateTime.now()),
                ), false, null
            ),
            onArticleClick = {}
        )
    }
}

@Preview
@Composable
fun ArticlesListScreenContentErrorPreview() {
    Surface {
        ArticleListScreenContent(
            state = ArticleListState(emptyList(), false, UiText.DynamicString("error")),
            onArticleClick = {}
        )
    }
}