package com.example.newsinshorts.features.article.data.remote

import com.example.newsinshorts.features.article.data.model.ArticleDTO
import com.example.newsinshorts.features.article.data.model.ArticlesListDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ArticleApiService {

    @GET("/v4/articles/")
    suspend fun fetchNewsList(
        @Query("limit") pageSize: Int,
        @Query("offset") pageNumber: Int,
    ): ArticlesListDTO

    @GET("/v4/articles/{id}")
    suspend fun fetchArticleDetails(@Path("id") id: Int): Response<ArticleDTO>
}