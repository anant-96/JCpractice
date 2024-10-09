package me.amitshekhar.newsapp.ui.topheadlinewithpaging

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import me.amitshekhar.newsapp.data.model.Article
import me.amitshekhar.newsapp.data.repository.TopHeadlineRepository
import javax.inject.Inject

@HiltViewModel
class TopHeadlineWithPagingViewModel @Inject constructor(private val topHeadlineRepository: TopHeadlineRepository) :
    ViewModel() {

    private val _uiState = MutableStateFlow<PagingData<Article>>(value = PagingData.empty())

    val uiState: StateFlow<PagingData<Article>> = _uiState

    init {
        getNews()
    }

    private fun getNews() {
        viewModelScope.launch {
            topHeadlineRepository.getTopHeadlinesWithPaging()
                .collect {
                    _uiState.value = it
                }
        }
    }
}
