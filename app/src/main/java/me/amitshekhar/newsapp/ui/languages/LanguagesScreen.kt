package me.amitshekhar.newsapp.ui.languages

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import me.amitshekhar.newsapp.data.model.Language
import me.amitshekhar.newsapp.ui.base.CreateHeading
import me.amitshekhar.newsapp.ui.base.ShowError
import me.amitshekhar.newsapp.ui.base.ShowLoading
import me.amitshekhar.newsapp.ui.base.UiState
import me.amitshekhar.newsapp.ui.topheadline.TopHeadlineScreen
import me.amitshekhar.newsapp.ui.topheadline.TopHeadlineViewModel
import me.amitshekhar.newsapp.utils.AppConstant

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LanguagesRoute(onItemClick: (String) -> Unit, viewModel: LanguagesViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    Scaffold(content = { padding ->
        Column(modifier = Modifier.padding(padding)) {
            LanguagesScreen(uiState, onItemClick)
        }
    })
}

@Composable
fun LanguagesScreen(uiState: UiState<List<Language>>, onItemClick: (String) -> Unit) {
    when (uiState) {
        is UiState.Success -> {
            LanguageHeading()
            LanguageList(uiState.data, onItemClick)
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
fun LanguageHeading() {
    CreateHeading(nameOfTheScreen = AppConstant.LANGUAGES)
}

@Composable
fun LanguageList(languages: List<Language>, onItemClick: (String) -> Unit) {
    LazyColumn {
        items(languages, key = { language -> language.id }) { language ->
            RenderLanguage(language, onItemClick)
        }
    }
}

@Composable
fun RenderLanguage(language: Language, onItemClick: (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp) // Add padding
            .background(Color.Cyan) // Add background color
            .clickable { onItemClick(language.id) }
            .border(1.dp, Color.Gray, RoundedCornerShape(8.dp)) // Add border with rounded corners
            .padding(8.dp) // Add padding within the border
    ) {
        Text(
            text = language.name,
            style = MaterialTheme.typography.titleMedium, // Apply typography style
            color = Color.Black, // Text color
            textAlign = TextAlign.Center, // Center align the text
            modifier = Modifier.fillMaxWidth() // Make the text occupy the entire width
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsByLanguageRoute(
    onNewsClick: (url: String) -> Unit,
    language: String,
    viewModel: TopHeadlineViewModel = hiltViewModel(),
) {
    viewModel.fetchNewsByLanguages(language)
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    Scaffold(content = { padding ->
        Column(modifier = Modifier.padding(padding)) {
            TopHeadlineScreen(uiState, onNewsClick)
        }
    })
}

