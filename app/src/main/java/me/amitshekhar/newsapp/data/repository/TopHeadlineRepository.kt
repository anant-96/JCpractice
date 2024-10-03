package me.amitshekhar.newsapp.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import me.amitshekhar.newsapp.data.api.NetworkService
import me.amitshekhar.newsapp.data.model.Article
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TopHeadlineRepository @Inject constructor(private val networkService: NetworkService) {

    fun getTopHeadlines(country: String): Flow<List<Article>> {
        return flow {
            emit(networkService.getTopHeadlines(country))
        }.map {
            it.articles
        }
    }

    fun getTopHeadlinesByLanguages(language: String): Flow<List<Article>> {
        return flow {
            emit(networkService.getTopHeadlinesByLanguage(language))
        }.map {
            it.articles }
    }
}
