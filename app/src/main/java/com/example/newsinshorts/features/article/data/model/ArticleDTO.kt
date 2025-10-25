package com.example.newsinshorts.features.article.data.model

import com.google.gson.annotations.SerializedName

data class ArticlesListDTO(
    @SerializedName("results") val result: List<ArticleDTO>
)

data class ArticleDTO(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("authors") val authors: List<AuthorDTO>,
    @SerializedName("url") val url: String,
    @SerializedName("image_url") val imageUrl: String,
    @SerializedName("summary") val summary: String,
    @SerializedName("published_at") val publishedAt: String,
)

data class AuthorDTO(
    @SerializedName("name") val name: String,
)