package me.amitshekhar.newsapp.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import me.amitshekhar.newsapp.data.model.Article
import me.amitshekhar.newsapp.data.repository.TopHeadlineRepository
import me.amitshekhar.newsapp.ui.base.UiState
import me.amitshekhar.newsapp.utils.AppConstant.DEBOUNCE_TIMEOUT
import me.amitshekhar.newsapp.utils.AppConstant.MIN_SEARCH_CHAR
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val repository: TopHeadlineRepository) :
    ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<Article>>>(UiState.Success(emptyList()))
    val uiState: StateFlow<UiState<List<Article>>> = _uiState

    private val _query = MutableStateFlow("")
    val query = _query

    init {
        createNewsFlow()
    }

    fun searchNews(searchQuery: String) {
        _query.value = searchQuery
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    private fun createNewsFlow() {
        viewModelScope.launch {
            _query.debounce(DEBOUNCE_TIMEOUT)
                .filter {
                    if (it.isNotEmpty() && it.length >= MIN_SEARCH_CHAR) {
                        return@filter true
                    } else {
                        _uiState.value = UiState.Success(emptyList())
                        return@filter false
                    }
                }
                .distinctUntilChanged()
                .flatMapLatest {
                    _uiState.value = UiState.Loading
                    return@flatMapLatest repository.getTopHeadlinesByKeyword(it)
                        .catch { e ->
                            // handle error properly
                            _uiState.value = UiState.Error(e.toString())
                        }
                }
                .flowOn(Dispatchers.IO)
                .collect {
                    // handle response and empty response properly
                    _uiState.value = UiState.Success(it)
                }
        }
    }
}
