package me.amitshekhar.newsapp.ui.newssources

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import me.amitshekhar.newsapp.data.model.Source
import me.amitshekhar.newsapp.data.repository.NewsSourcesRepository
import me.amitshekhar.newsapp.ui.base.UiState
import javax.inject.Inject

@HiltViewModel
class NewsSourcesViewModel @Inject constructor(private val newsSourcesRepository: NewsSourcesRepository) :
    ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<Source>>>(UiState.Loading)
    val uiState: StateFlow<UiState<List<Source>>> = _uiState

    init {
        fetchNewsSources()
    }

    private fun fetchNewsSources() {
        viewModelScope.launch {
            newsSourcesRepository.getNewsSources()
                .catch { e ->
                    _uiState.value = UiState.Error(e.toString())
                }.collect {
                    _uiState.value = UiState.Success(it)
                }
        }
    }
}
