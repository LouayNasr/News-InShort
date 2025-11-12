package com.example.newsinshorts.features.article.presentation.article_details

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import coil3.compose.AsyncImage
import com.example.newsinshorts.R
import com.example.newsinshorts.features.article.domain.model.Article
import com.example.newsinshorts.features.article.presentation.components.ErrorScreen
import com.example.newsinshorts.features.article.presentation.components.LoadingScreen
import java.time.LocalDateTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticleDetailsScreen(
    viewModel: ArticleDetailsViewModel = hiltViewModel(),
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

        when {

            state.isLoading -> LoadingScreen(modifier = Modifier.padding(paddingValues))
            state.errorMessage != null -> ErrorScreen(
                state.errorMessage.asString(),
                onRetry = { viewModel.fetchArticleDetails(viewModel.articleId) },
                modifier = Modifier.padding(paddingValues),
            )

            else -> {
                ArticleDetailsScreenContent(
                    state = state,
                    modifier = Modifier.padding(paddingValues)
                )
            }
        }
        ArticleDetailsScreenContent(
            state = state,
            modifier = Modifier
                .padding(paddingValues)
        )
    }
}

@Composable
fun ArticleDetailsScreenContent(
    state: ArticleDetailsState,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize(),
    ) {
        state.article?.let {
            Column(modifier = Modifier) {
                AsyncImage(
                    model = state.article.imageUrl,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    contentDescription = null
                )
                Spacer(modifier = Modifier.size(16.dp))
                Text(
                    text = it.title,
                    style = MaterialTheme.typography.headlineSmall,
                    color = Color.LightGray,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                Spacer(modifier = Modifier.size(8.dp))
                Text(
                    text = it.publishedAt,
                    style = MaterialTheme.typography.labelLarge,
                    color = Color.DarkGray,
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .align(Alignment.End)
                )
                Spacer(modifier = Modifier.size(16.dp))
                Text(
                    text = it.summary,
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.Gray,
                    modifier = Modifier.padding(horizontal = 16.dp)

                )
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun ArticleDetailsScreenContentSuccessPreview() {
    Surface(modifier = Modifier.fillMaxSize()) {
        ArticleDetailsScreenContent(
            state = ArticleDetailsState(
                Article(
                    1,
                    "title",
                    emptyList(),
                    "",
                    "https://fastly.picsum.photos/id/13/2500/1667.jpg?hmac=SoX9UoHhN8HyklRA4A3vcCWJMVtiBXUg0W4ljWTor7s",
                    "With the end of the 2025 school year fast approaching here in Australia, many students and parents will be turning their minds to how to show appreciation to the educators who’ve made a difference to them. From candles and soaps, to mugs and homemade baked goods – each year educators are swamped with heartfelt gestures of appreciation. \n" +
                            "\n" +
                            "When Andrea McKellar, a grade 6 teacher at Holy Spirit Community School in Victoria, saw how many lovely gifts she received each year (and the amount of money that parents collectively spent) she started to think if there was an alternative, and had an idea. \n" +
                            "\n" +
                            "\n" +
                            "‘I'm extremely lucky in my life that I have everything that I need and everything I want,’ McKellar tells Teacher. ‘So, these families saying thank you with a gift – as much as I love the sentiment, I don't need a candle, and I don't need a mug. But I know within the world and within our community, there are lots of people who need things.’\n" +
                            "\n" +
                            "In 2019, McKellar was listening to a radio segment featuring local businessman Mark Balla who was sharing a story about a trip he made to India, where he found that many schools had no toilets. From there, she met with Balla at a coffee shop and heard all about his efforts to get more girls to school in India via his not-for-profit called Operation Toilets. \n" +
                            "\n" +
                            "This inspired McKellar to put her own idea into action. She started Teacher Presence – a not-for-profit organisation that redirects money that would otherwise be used for optional educator gifts for occasions like the end of the school year, or certain educator celebrations (World Teacher’s Day, Early Educators Day, staff birthdays) to charities aligned with their mission. \n" +
                            "\n" +
                            "Their mission is to create a community of educators that spread the importance of kindness, empathy and gratitude through the gift of giving. \n" +
                            "\n" +
                            "‘So rather than me getting \$450 worth of candles and gifts, that money now goes to help build toilets in India and those girls can have the opportunity to go to school,’ McKellar shares.\n" +
                            "\n" +
                            "Educators choose to join Teacher Presence if it resonates with them. Everyone working on Teacher Presence volunteers their time, as 100% of all donations go directly to the teacher’s nominated charity.\n" +
                            "\n",
                    "2015"
                ),
                false,
                null
            )
        )
    }
}