package com.example.newsinshorts

import androidx.navigation.NavHostController
import com.example.newsinshorts.NewsDestinationArgs.ARTICLE_ID_ARG
import com.example.newsinshorts.NewsScreens.ARTICLES_SCREEN
import com.example.newsinshorts.NewsScreens.ARTICLE_DETAILS_SCREEN


private object NewsScreens {
    const val ARTICLES_SCREEN = "articles"
    const val ARTICLE_DETAILS_SCREEN = "articleDetails"
}

object NewsDestinationArgs {
    const val ARTICLE_ID_ARG = "articleId"
}

object NewsDestinations {
    const val ARTICLES_LIST_ROUTE = "$ARTICLES_SCREEN"
    const val ARTICLE_DETAILS_ROUTE = "$ARTICLE_DETAILS_SCREEN/{$ARTICLE_ID_ARG}"
}

class NewsNavigationActions(private val navController: NavHostController) {

    fun navigateToArticles() {
        navController.navigate(
            ARTICLES_SCREEN
        ) {
            launchSingleTop = true
        }
    }

    fun navigateToArticleDetails(articleId: Int) {
        navController.navigate("$ARTICLE_DETAILS_SCREEN/$articleId")
    }
}