package com.example.newsinshorts.features.article.data.mapper

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.newsinshorts.features.article.data.model.ArticleDTO
import com.example.newsinshorts.features.article.data.model.AuthorDTO
import com.example.newsinshorts.features.article.domain.model.Article
import com.example.newsinshorts.features.article.domain.model.Author
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter


@RequiresApi(Build.VERSION_CODES.O)
fun ArticleDTO.toArticle(): Article {
    return Article(
        id = id,
        title = title,
        authors = authors.map { it.toAuthor() },
        url = url,
        imageUrl = imageUrl,
        summary = summary,
        publishedAt = OffsetDateTime.parse(publishedAt).format(DateTimeFormatter.ofPattern("dd-MM-yyyy")),
    )
}

fun AuthorDTO.toAuthor(): Author {
    return Author(
        name = name
    )
}
