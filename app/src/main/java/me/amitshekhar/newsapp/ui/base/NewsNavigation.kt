package me.amitshekhar.newsapp.ui.base

import android.content.Context
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import me.amitshekhar.newsapp.ui.HomeScreenRoute
import me.amitshekhar.newsapp.ui.topheadline.TopHeadlineRoute

sealed class Route(val name: String) {
    object TopHeadline : Route("topheadline")
    object Home : Route("home")
    object NewsSource : Route("newssource")
    object Countries : Route("countries")
    object Language : Route("language")
    object Search : Route("search")
}

@Composable
fun NewsNavHost() {

    val navController = rememberNavController()
    val context = LocalContext.current

    NavHost(
        navController = navController,
        startDestination = Route.Home.name
    ) {
        composable(route = Route.Home.name) {
            HomeScreenRoute(onItemClick = { navigateTo(it, navController) })
        }
        composable(route = Route.TopHeadline.name) {
            TopHeadlineRoute(onNewsClick = {
                openCustomChromeTab(context, it)
            })
        }
    }

}

fun openCustomChromeTab(context: Context, url: String) {
    val builder = CustomTabsIntent.Builder()
    val customTabsIntent = builder.build()
    customTabsIntent.launchUrl(context, Uri.parse(url))
}

fun navigateTo(route: Route, navController: NavController) {
    navController.navigate(route.name)
}

