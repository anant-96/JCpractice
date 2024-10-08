package me.amitshekhar.newsapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import me.amitshekhar.newsapp.data.model.HomeItem
import me.amitshekhar.newsapp.ui.base.Route
import me.amitshekhar.newsapp.utils.AppConstant.COUNTRIES
import me.amitshekhar.newsapp.utils.AppConstant.LANGUAGES
import me.amitshekhar.newsapp.utils.AppConstant.OFFLINE_TOP_HEADLINES
import me.amitshekhar.newsapp.utils.AppConstant.SEARCH
import me.amitshekhar.newsapp.utils.AppConstant.SOURCES
import me.amitshekhar.newsapp.utils.AppConstant.TOP_HEADLINES


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenRoute(
    onItemClick: (url: Route) -> Unit
) {
    Scaffold(content = { padding ->
        Column(modifier = Modifier.padding(padding)) {
            HomeScreen(onItemClick)
        }
    })
}

@Composable
fun HomeScreen(onItemClick: (url: Route) -> Unit) {
    val homeScreenList = ArrayList<HomeItem>()
    homeScreenList.add(HomeItem(1, Route.TopHeadline, TOP_HEADLINES))
    homeScreenList.add(HomeItem(2, Route.NewsSource, SOURCES))
    homeScreenList.add(HomeItem(3, Route.Countries, COUNTRIES))
    homeScreenList.add(HomeItem(4, Route.Language, LANGUAGES))
    homeScreenList.add(HomeItem(5, Route.Search, SEARCH))
    homeScreenList.add(HomeItem(6, Route.OfflineTopHeadline, OFFLINE_TOP_HEADLINES))
    LazyColumn(modifier = Modifier.fillMaxHeight(), verticalArrangement = Arrangement.Center) {
        items(homeScreenList, key = { homeItem -> homeItem.id }) { homeItem ->
            HomeItemComposable(homeItem, onItemClick)
        }
    }
}

@Composable
fun HomeItemComposable(homeItem: HomeItem, onClick: (Route) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp) // Add padding
            .background(Color(0xFF3700B3)) // Add background color
            .clickable { onClick(homeItem.route) }
            .border(1.dp, Color.Gray, RoundedCornerShape(12.dp)) // Add border with rounded corners
            .padding(8.dp) // Add padding within the border
    ) {
        Text(
            text = homeItem.name,
            style = MaterialTheme.typography.titleMedium, // Apply typography style
            color = Color.White, // Text color
            textAlign = TextAlign.Center, // Center align the text
            modifier = Modifier.fillMaxWidth() // Make the text occupy the entire width
        )
    }
}
