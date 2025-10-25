package com.example.newsinshorts.features.article.domain.model

import java.time.LocalDateTime

data class Article(
    val id: Int,
    val title: String,
    val authors: List<Author>,
    val url: String,
    val imageUrl: String,
    val summary: String,
    val publishedAt: LocalDateTime,
)

data class Author(
    val name: String,
)
