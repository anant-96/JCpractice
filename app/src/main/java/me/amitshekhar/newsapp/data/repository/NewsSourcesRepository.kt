package me.amitshekhar.newsapp.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import me.amitshekhar.newsapp.data.api.NetworkService
import me.amitshekhar.newsapp.data.model.Source
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsSourcesRepository @Inject constructor(private val networkService: NetworkService) {
    fun getNewsSources(): Flow<List<Source>> {
        return flow {
            emit(networkService.getNewsSource())
        }.map { it.sources }
    }
}
