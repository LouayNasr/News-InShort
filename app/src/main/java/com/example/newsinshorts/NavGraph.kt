package com.example.newsinshorts

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.newsinshorts.features.article.presentation.article_details.ArticleDetailsScreen
import com.example.newsinshorts.features.article.presentation.article_list.ArticleListScreen

@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController(),
    startDestination: String = NewsDestinations.ARTICLES_LIST_ROUTE,
    navActions: NewsNavigationActions = remember(navController) {
        NewsNavigationActions(navController)
    }
) {

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(NewsDestinations.ARTICLES_LIST_ROUTE) {
            ArticleListScreen(
                onArticleClick = { articleId ->
                    navActions.navigateToArticleDetails(articleId)
                }
            )
        }

        composable(NewsDestinations.ARTICLE_DETAILS_ROUTE) {
            ArticleDetailsScreen()
        }
    }
}