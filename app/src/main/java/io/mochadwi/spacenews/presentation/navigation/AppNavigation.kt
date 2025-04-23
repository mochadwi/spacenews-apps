package io.mochadwi.spacenews.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import io.mochadwi.spacenews.presentation.auth.AuthScreen // Placeholder import

object Routes {
    const val AUTH = "auth"
    const val HOME = "home"
    const val ARTICLE_DETAIL = "article_detail/{articleId}" // Example with argument
    const val RECENT_SEARCH = "recent_search"

    fun articleDetail(articleId: Long): String = "article_detail/$articleId"
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.AUTH) {
        composable(Routes.AUTH) {
            // TODO: Replace with actual AuthScreen
             AuthScreen(navController = navController)
        }
        composable(Routes.HOME) {
             // TODO: Replace with actual HomeScreen
             // HomeScreen(navController = navController)
        }
        composable(Routes.ARTICLE_DETAIL) { backStackEntry ->
            val articleId = backStackEntry.arguments?.getString("articleId")?.toLongOrNull()
            // TODO: Replace with actual ArticleDetailScreen, handle null articleId
             if (articleId != null) {
                 // ArticleDetailScreen(articleId = articleId, navController = navController)
             } else {
                 // Handle error: navigate back or show error message
                 navController.popBackStack()
             }
        }
        composable(Routes.RECENT_SEARCH) {
             // TODO: Replace with actual RecentSearchScreen
             // RecentSearchScreen(navController = navController)
        }
        // Add other destinations (e.g., BlogList, ReportList if they are separate screens)
    }
}

// Placeholder composables removed as they are now in separate files.