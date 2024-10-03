package me.amitshekhar.newsapp.ui.languages

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import me.amitshekhar.newsapp.data.model.Language
import me.amitshekhar.newsapp.data.repository.LanguagesRepository
import me.amitshekhar.newsapp.ui.base.UiState
import javax.inject.Inject

@HiltViewModel
class LanguagesViewModel @Inject constructor(private val languagesRepository: LanguagesRepository) :
    ViewModel() {
    private val _uiState = MutableStateFlow<UiState<List<Language>>>(UiState.Loading)
    val uiState: StateFlow<UiState<List<Language>>> = _uiState

    init {
        fetchLanguages()
    }

    private fun fetchLanguages() {
        viewModelScope.launch {
            languagesRepository.getLanguages().flowOn(Dispatchers.Default)
                .catch { e ->
                    _uiState.value = UiState.Error(e.toString())
                }.collect {
                    _uiState.value = UiState.Success(it)
                }
        }
    }
}
