package me.amitshekhar.newsapp.ui.offlinearticles

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import me.amitshekhar.newsapp.data.local.entity.LocalArticle
import me.amitshekhar.newsapp.data.repository.OfflineArticleRepository
import me.amitshekhar.newsapp.di.module.NetworkHelper
import me.amitshekhar.newsapp.ui.base.UiState
import me.amitshekhar.newsapp.utils.AppConstant.COUNTRY
import javax.inject.Inject

@HiltViewModel
class OfflineArticleViewModel @Inject constructor(
    networkHelper: NetworkHelper,
    private val offlineArticleRepository: OfflineArticleRepository
) :
    ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<LocalArticle>>>(UiState.Loading)

    val uiState: StateFlow<UiState<List<LocalArticle>>> = _uiState

    init {
        // First check if internet is connected, if yes then fetch from internet else fetch from DB
        if (networkHelper.isNetworkConnected()) {
            fetchArticles()
        } else {
            fetchArticlesDirectlyFromDB()
        }
    }

    private fun fetchArticles() {
        viewModelScope.launch {
            offlineArticleRepository.getArticles(COUNTRY)
                .flowOn(Dispatchers.IO)
                .catch { e ->
                    _uiState.value = UiState.Error(e.toString())
                }.collect {
                    _uiState.value = UiState.Success(it)
                }
        }
    }

    private fun fetchArticlesDirectlyFromDB() {
        viewModelScope.launch {
            offlineArticleRepository.getArticlesDirectlyFromDB()
                .flowOn(Dispatchers.IO)
                .catch { e ->
                    _uiState.value = UiState.Error(e.toString())
                }.collect {
                    _uiState.value = UiState.Success(it)
                }
        }
    }

}
