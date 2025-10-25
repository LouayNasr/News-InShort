package com.example.newsinshorts.features.article.data.mapper

import com.example.newsinshorts.features.article.data.model.ArticleDTO
import com.example.newsinshorts.features.article.data.model.AuthorDTO
import com.example.newsinshorts.features.article.data.model.ArticlesListDTO
import com.example.newsinshorts.features.article.domain.model.Article
import com.example.newsinshorts.features.article.domain.model.Author
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


fun ArticleDTO.toArticle(): Article {
    return Article(
        id = id,
        title = title,
        authors = authors.map { it.toAuthor() },
        url = url,
        imageUrl = imageUrl,
        summary = summary,
        publishedAt = LocalDateTime.parse(publishedAt, DateTimeFormatter.ISO_DATE_TIME),
    )
}

fun AuthorDTO.toAuthor(): Author {
    return Author(
        name = name
    )
}
