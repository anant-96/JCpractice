package me.amitshekhar.newsapp.ui.countries

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import me.amitshekhar.newsapp.data.model.Country
import me.amitshekhar.newsapp.data.repository.CountriesRepository
import me.amitshekhar.newsapp.ui.base.UiState
import javax.inject.Inject

@HiltViewModel
class CountriesViewModel @Inject constructor(private val countriesRepository: CountriesRepository) :
    ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<Country>>>(UiState.Loading)
    val uiState: StateFlow<UiState<List<Country>>> = _uiState

    init {
        fetchCountries()
    }

    private fun fetchCountries() {
        viewModelScope.launch {
            countriesRepository.getCountries()
                .flowOn(Dispatchers.Default)
                .catch { e ->
                    _uiState.value = UiState.Error(e.toString())
                }.collect {
                    _uiState.value = UiState.Success(it)
                }
        }
    }
}
