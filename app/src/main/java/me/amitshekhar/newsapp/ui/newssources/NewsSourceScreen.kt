package me.amitshekhar.newsapp.ui.newssources

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import me.amitshekhar.newsapp.data.model.Source
import me.amitshekhar.newsapp.ui.base.CreateHeading
import me.amitshekhar.newsapp.ui.base.ShowError
import me.amitshekhar.newsapp.ui.base.ShowLoading
import me.amitshekhar.newsapp.ui.base.UiState
import me.amitshekhar.newsapp.utils.AppConstant.SOURCES

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsSourceRoute(
    onNewsClick: (url: String) -> Unit, viewModel: NewsSourcesViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    Scaffold(content = { padding ->
        Column(modifier = Modifier.padding(padding)) {
            NewsSourceScreen(uiState, onNewsClick)
        }
    })
}

@Composable
fun NewsSourceScreen(uiState: UiState<List<Source>>, onNewsClick: (url: String) -> Unit) {
    when (uiState) {
        is UiState.Success -> {
            SourceHeading()
            SourceList(uiState.data, onNewsClick)
        }

        is UiState.Error -> {
            ShowError(uiState.message)
        }

        else -> {
            ShowLoading()
        }
    }
}

@Composable
fun SourceHeading() {
    CreateHeading(nameOfTheScreen = SOURCES)
}

@Composable
fun SourceList(sources: List<Source>, onNewsClick: (url: String) -> Unit) {
    LazyColumn {
        items(sources, key = { article -> article.url }) { source ->
            Source(source, onNewsClick)
        }
    }
}

@Composable
fun Source(source: Source, onNewsClick: (url: String) -> Unit) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .border(
            width = 1.dp, color = Color.Gray, shape = RoundedCornerShape(8.dp)
        ) // Add border
        .clickable {
            if (source.url.isNotEmpty()) {
                onNewsClick(source.url)
            }
        }) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .background(Color.Cyan)
        ) {
            TitleText(source.name)
            Spacer(modifier = Modifier.height(8.dp))
            DescriptionText(source.description)
        }
    }
}

@Composable
fun TitleText(title: String) {
    if (title.isNotEmpty()) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            color = Color.Black,
            maxLines = 2,
            modifier = Modifier.padding(4.dp)
        )
    }
}

@Composable
fun DescriptionText(description: String?) {
    if (!description.isNullOrEmpty()) {
        Text(
            text = description,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray,
            maxLines = 2,
            modifier = Modifier.padding(4.dp)
        )
    }
}
