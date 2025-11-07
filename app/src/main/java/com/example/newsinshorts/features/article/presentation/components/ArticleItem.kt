package com.example.newsinshorts.features.article.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.newsinshorts.features.article.domain.model.Article

@Composable
fun ArticleItem(
    article: Article?,
    onArticleClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(96.dp)
            .padding(vertical = 4.dp)
            .clickable { onArticleClick(article!!.id) },
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = article!!.imageUrl,
            modifier = Modifier
                .height(64.dp)
                .width(83.dp)
                .clip(RoundedCornerShape(8.dp)),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(
            modifier = Modifier
                .padding(vertical = 12.dp)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = article!!.title,
                style = MaterialTheme.typography.bodyLarge,
                color = Color.LightGray,
                maxLines = 2
            )
            Text(
                text = article!!.publishedAt,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )
        }
    }
}