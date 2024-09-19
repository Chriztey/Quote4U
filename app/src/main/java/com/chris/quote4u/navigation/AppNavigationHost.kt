package com.chris.quote4u.navigation

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
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

        composable<HomeScreenRoute>(
            enterTransition = ::slideInToRight,
            exitTransition = ::slideOutToUp,
            popExitTransition = ::slideOutToUp,
            popEnterTransition = ::slideInToLeft
        ) {
            HomeScreen(
                navigateToSavedQuotes = {navHost.navigate(SavedQuoteListScreenRoute)}
            )
        }

        composable<SavedQuoteListScreenRoute>(
            enterTransition = ::slideInToRight,
            popExitTransition = ::slideOutToLeft,
            popEnterTransition = ::slideInToUp
        ) {
            SavedQuoteListScreen(
                navigateToHome = {navHost.navigate(HomeScreenRoute)},
                navigateToItemPage = {navHost.navigate(SavedQuoteItemScreenRoute(it))}
            )
        }

        composable<SavedQuoteItemScreenRoute>(
            enterTransition = {
                fadeIn(
                    animationSpec = tween(
                        300, easing = LinearEasing
                    )
                ) + slideIntoContainer(
                    animationSpec = tween(300, easing = EaseIn),
                    towards = AnimatedContentTransitionScope.SlideDirection.Start
                )
            },
            exitTransition = {
                fadeOut(
                    animationSpec = tween(
                        300, easing = LinearEasing
                    )
                ) + slideOutOfContainer(
                    animationSpec = tween(300, easing = EaseOut),
                    towards = AnimatedContentTransitionScope.SlideDirection.End
                )
            },
            popExitTransition = ::slideOutToLeft,
            popEnterTransition = ::slideInToLeft
        ) {
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
object GetStartedScreenRoute
@Serializable
object HomeScreenRoute
@Serializable
object SavedQuoteListScreenRoute
@Serializable
data class SavedQuoteItemScreenRoute(
    val quoteId: Int
)

fun slideOutToLeft(
    scope: AnimatedContentTransitionScope<NavBackStackEntry>
): ExitTransition {
    return scope.slideOutOfContainer(
        AnimatedContentTransitionScope.SlideDirection.Left,
        animationSpec = tween(300)
    )
}

fun slideOutToRight(
    scope: AnimatedContentTransitionScope<NavBackStackEntry>
): ExitTransition {
    return scope.slideOutOfContainer(
        AnimatedContentTransitionScope.SlideDirection.Right,
        animationSpec = tween(300)
    )
}

fun slideOutToUp(
    scope: AnimatedContentTransitionScope<NavBackStackEntry>
): ExitTransition {
    return scope.slideOutOfContainer(
        AnimatedContentTransitionScope.SlideDirection.Up,
        animationSpec = tween(300)
    )
}

fun slideOutToDown(
    scope: AnimatedContentTransitionScope<NavBackStackEntry>
): ExitTransition {
    return scope.slideOutOfContainer(
        AnimatedContentTransitionScope.SlideDirection.Down,
        animationSpec = tween(300)
    )
}

fun slideInToLeft(
    scope: AnimatedContentTransitionScope<NavBackStackEntry>
): EnterTransition {
    return scope.slideIntoContainer(
        AnimatedContentTransitionScope.SlideDirection.Down,
        animationSpec = tween(300)
    )
}

fun slideInToRight(
    scope: AnimatedContentTransitionScope<NavBackStackEntry>
): EnterTransition {
    return scope.slideIntoContainer(
        AnimatedContentTransitionScope.SlideDirection.Right,
        animationSpec = tween(300)
    )
}


fun slideInToUp(
    scope: AnimatedContentTransitionScope<NavBackStackEntry>
): EnterTransition {
    return scope.slideIntoContainer(
        AnimatedContentTransitionScope.SlideDirection.Up,
        animationSpec = tween(300)
    )
}