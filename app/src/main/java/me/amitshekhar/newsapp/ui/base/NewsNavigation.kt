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
import me.amitshekhar.newsapp.ui.countries.CountriesRoute
import me.amitshekhar.newsapp.ui.countries.NewsByCountryRoute
import me.amitshekhar.newsapp.ui.languages.LanguagesRoute
import me.amitshekhar.newsapp.ui.languages.NewsByLanguageRoute
import me.amitshekhar.newsapp.ui.newssources.NewsSourceRoute
import me.amitshekhar.newsapp.ui.offlinearticles.OfflineArticleRoute
import me.amitshekhar.newsapp.ui.search.SearchRoute
import me.amitshekhar.newsapp.ui.topheadline.TopHeadlineRoute

sealed class Route(val name: String) {
    object TopHeadline : Route("topheadline")
    object Home : Route("home")

    object OfflineTopHeadline : Route("offlinetopheadline")
    object NewsSource : Route("newssource")
    object Countries : Route("countries")
    object NewsByCountry : Route("{country}/newsbycountry")
    object Language : Route("language")
    object NewsByLanguage : Route("{language}/newsbylanguage")
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
        composable(route = Route.OfflineTopHeadline.name) {
            OfflineArticleRoute(onNewsClick = { openCustomChromeTab(context, it) })
        }
        composable(route = Route.NewsSource.name) {
            NewsSourceRoute(onNewsClick = {
                openCustomChromeTab(context, it)
            })
        }

        // Countries
        composable(route = Route.Countries.name) {
            CountriesRoute(onItemClick = {
                navController.navigate("$it/newsbycountry")
            })
        }
        composable(route = Route.NewsByCountry.name) {
            val country = it.arguments?.getString("country")
            if (country != null) {
                NewsByCountryRoute(
                    onNewsClick = { url -> openCustomChromeTab(context, url) }, country = country
                )
            }
        }

        // Languages
        composable(route = Route.Language.name) {
            LanguagesRoute(onItemClick = {
                navController.navigate("$it/newsbylanguage")
            })
        }
        composable(route = Route.NewsByLanguage.name) {
            val language = it.arguments?.getString("language")
            if (language != null) {
                NewsByLanguageRoute(
                    onNewsClick = { url -> openCustomChromeTab(context, url) }, language = language
                )
            }
        }

        composable(route = Route.Search.name) {
            SearchRoute(onNewsClick = { openCustomChromeTab(context, it) })
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

