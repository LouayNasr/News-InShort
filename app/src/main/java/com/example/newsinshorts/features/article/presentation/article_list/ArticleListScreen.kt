package com.example.newsinshorts.features.article.presentation.article_list

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.newsinshorts.R
import com.example.newsinshorts.features.article.domain.model.Article
import com.example.newsinshorts.features.article.presentation.components.AppendErrorItem
import com.example.newsinshorts.features.article.presentation.components.ArticleItem
import com.example.newsinshorts.features.article.presentation.components.ErrorScreen
import com.example.newsinshorts.features.article.presentation.components.LoadingMoreItem
import com.example.newsinshorts.features.article.presentation.components.LoadingScreen
import kotlinx.coroutines.flow.flowOf
import java.time.LocalDateTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticleListScreen(
    viewModel: ArticleListViewModel = hiltViewModel(),
    onArticleClick: (Int) -> Unit,
) {
    val articles: LazyPagingItems<Article> = viewModel.articles.collectAsLazyPagingItems()
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
        val isRefreshing = articles.loadState.refresh is LoadState.Loading
        val isError = articles.loadState.refresh is LoadState.Error
        when {

            isRefreshing -> LoadingScreen(modifier = Modifier.padding(paddingValues))
            isError -> {
                if (articles.itemCount == 0) {
                    val error = (articles.loadState.refresh as LoadState.Error).error
                    ErrorScreen(
                        message = error.localizedMessage ?: "Unknown error",
                        onRetry = { articles.retry() },
                        modifier = Modifier.padding(paddingValues)
                    )
                }
            }

            else -> {
                ArticleListScreenContent(
                    articles = articles,
                    onArticleClick = onArticleClick,
                    modifier = Modifier.padding(paddingValues)
                )
            }
        }
    }
}

@Composable
fun ArticleListScreenContent(
    articles: LazyPagingItems<Article>,
    onArticleClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val isAppending = articles.loadState.append is LoadState.Loading
    val error = (articles.loadState.append as? LoadState.Error)?.error

    Box(
        modifier = modifier
            .padding(horizontal = 8.dp)
    ) {
        when {
            else -> {
                LazyColumn {
                    items(articles.itemCount) { index ->
                        articles[index]?.let {
                            ArticleItem(
                                it,
                                onArticleClick = onArticleClick
                            )
                        }
                    }
                    if (isAppending) {
                        item { LoadingMoreItem() }
                    }
                    if (error != null) {
                        item {
                            AppendErrorItem(
                                message = error.localizedMessage ?: "Failed to load more",
                                onRetry = { articles.retry() }
                            )
                        }
                    }
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun ArticlesListScreenContentSuccessPreview() {
    Surface {
        ArticleListScreenContent(
            articles = listOf(
                Article(
                    1,
                    "title",
                    emptyList(),
                    "",
                    "https://camo.githubusercontent.com/3cae61090608b8cbd681f5825ca5ac76af8d8d3ee12024926d51c5480aef5d6c/68747470733a2f2f796176757a63656c696b65722e6769746875622e696f2f73616d706c652d696d616765732f696d6167652d313032312e6a7067",
                    "",
                    LocalDateTime.now().toString()
                ),
                Article(
                    1,
                    "another title",
                    emptyList(),
                    "",
                    "",
                    "",
                    LocalDateTime.now().toString()
                )
            ).toLazyPagingItems(),
            {},
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun ArticlesListScreenContentErrorPreview() {
    Surface {
        ArticleListScreenContent(
            articles = listOf(
                Article(
                    1,
                    "title",
                    emptyList(),
                    "",
                    "https://camo.githubusercontent.com/3cae61090608b8cbd681f5825ca5ac76af8d8d3ee12024926d51c5480aef5d6c/68747470733a2f2f796176757a63656c696b65722e6769746875622e696f2f73616d706c652d696d616765732f696d6167652d313032312e6a7067",
                    "",
                    LocalDateTime.now().toString()
                ),
                Article(
                    1,
                    "another title",
                    emptyList(),
                    "",
                    "",
                    "",
                    LocalDateTime.now().toString()
                )
            ).toLazyPagingItems(),
            {},
        )
    }
}

@Composable
fun <T : Any> List<T>.toLazyPagingItems(): LazyPagingItems<T> {
    val pagingData = remember { PagingData.from(this) }
    return flowOf(pagingData).collectAsLazyPagingItems()
}