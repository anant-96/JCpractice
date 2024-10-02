package me.amitshekhar.newsapp.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import me.amitshekhar.newsapp.data.model.Country
import me.amitshekhar.newsapp.utils.AppConstant
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CountriesRepository @Inject constructor() {
    fun getCountries(): Flow<List<Country>> {
        return flow {
            emit(AppConstant.COUNTRYLIST)
        }
    }
}
