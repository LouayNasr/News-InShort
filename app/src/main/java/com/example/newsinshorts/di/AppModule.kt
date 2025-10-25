package com.example.newsinshorts.di

import com.example.newsinshorts.features.article.data.remote.ArticleApiService
import com.example.newsinshorts.features.article.data.repository.ArticlesRepositoryImpl
import com.example.newsinshorts.features.article.domain.repository.ArticlesRepository
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

// TODO: check if this file has to be moved to di package or not
@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    private const val BASE_URL = "https://api.spaceflightnewsapi.net/";

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder()
                        .setLenient()
                        .create()
                )
            )
            .build()
    }

    @Provides
    @Singleton
    fun provideArticleApiService(retrofit: Retrofit): ArticleApiService {
        return retrofit.create(ArticleApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideArticlesRepository(articleApiService: ArticleApiService): ArticlesRepository {
        return ArticlesRepositoryImpl(articleApiService)
    }
}