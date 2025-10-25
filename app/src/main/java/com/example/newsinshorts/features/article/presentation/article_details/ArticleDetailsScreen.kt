package com.example.newsinshorts.features.article.presentation.article_details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import coil3.compose.AsyncImage
import com.example.newsinshorts.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticleDetailsScreen(
    viewModel: ArticleDetailsViewModel = hiltViewModel(),
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
            modifier = modifier.padding(paddingValues),
        ) {
            viewModel.state.article?.let {
                Column(modifier = Modifier) {
                    AsyncImage(
                        model = viewModel.state.article?.imageUrl,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(180.dp),
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.size(16.dp))
                    Text(
                        text = it.title,
                        style = MaterialTheme.typography.headlineSmall,
                        color = Color.DarkGray
                    )
                    Text(
                        text = it.summary,
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.LightGray
                    )
                }
            }
            if (viewModel.state.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            viewModel.state.errorMessage?.let {
                Text(
                    text = it.toString(),
                    textAlign = TextAlign.Center,
                    color = Color.Red,
                    modifier = Modifier.align(Alignment.Center)
                )

            }
        }
    }
}