package com.chris.quote4u.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.chris.quote4u.screen.HomeScreen
import com.chris.quote4u.screen.SavedQuoteListScreen
import com.chris.quote4u.screen.ViewSavedQuoteScreen
import kotlinx.serialization.Serializable

@Composable
fun AppNavigationHost () {

    val navHost = rememberNavController()

    NavHost(
        navController = navHost,
        startDestination = HomeScreenRoute) {

        composable<HomeScreenRoute> {
            HomeScreen(
                navigateToSavedQuotes = {navHost.navigate(SavedQuoteListScreenRoute)}
            )
        }

        composable<SavedQuoteListScreenRoute> {
            SavedQuoteListScreen(
                navigateToHome = {navHost.navigate(HomeScreenRoute)},
                navigateToItemPage = {navHost.navigate(SavedQuoteItemScreenRoute(it))}
            )
        }

        composable<SavedQuoteItemScreenRoute> {
            val args = it.toRoute<SavedQuoteItemScreenRoute>()
            ViewSavedQuoteScreen(
                quoteId = args.quoteId,
                navigateToHome = {navHost.navigate(HomeScreenRoute)},
                navigateBack = {navHost.popBackStack()}
            )
        }

    }

}

@Serializable
object HomeScreenRoute
@Serializable
object SavedQuoteListScreenRoute
@Serializable
data class SavedQuoteItemScreenRoute(
    val quoteId: Int
)