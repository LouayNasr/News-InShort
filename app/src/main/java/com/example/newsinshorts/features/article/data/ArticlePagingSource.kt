package com.example.newsinshorts.features.article.data

import android.net.http.HttpException
import android.nfc.tech.MifareUltralight.PAGE_SIZE
import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.newsinshorts.features.article.data.mapper.toArticle
import com.example.newsinshorts.features.article.data.remote.ArticleApiService
import com.example.newsinshorts.features.article.domain.model.Article
import java.io.IOException

class ArticlePagingSource(
    private val apiService: ArticleApiService
) : PagingSource<Int, Article>() { // Int is the key (page number), Article is the data type

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        // The current page number, defaulting to 1 if it's the first load
        val pageNumber = params.key ?: 1
        // Calculate the offset for the API call
        val offset = (pageNumber - 1) * PAGE_SIZE

        return try {
            // Fetch data from the API
            val response = apiService.fetchNewsList(
                pageSize = PAGE_SIZE,
                pageNumber = offset
            )
            val articles = response.result.map { it.toArticle() } // Assuming your ArticlesListDTO has a 'results' list

            // Determine the next page key
            val nextKey = if (articles.isNotEmpty()) {
                pageNumber + 1
            } else {
                // No more pages to load
                null
            }

            // Return a successful page load
            LoadResult.Page(
                data = articles,
                prevKey = if (pageNumber == 1) null else pageNumber - 1, // Only go back if not on the first page
                nextKey = nextKey
            )
        } catch (e: IOException) {
            // Handle network errors
            LoadResult.Error(e)
        } catch (e: HttpException) {
            // Handle HTTP errors (e.g., 404, 500)
            LoadResult.Error(e)
        }
    }

    // This function is used to refresh data. It determines which page to load
    // when the data is invalidated.
    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        // Try to find the page key of the closest page to the last accessed index
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}