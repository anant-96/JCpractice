package me.amitshekhar.newsapp.data.repository

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import me.amitshekhar.newsapp.data.api.NetworkService
import me.amitshekhar.newsapp.data.local.DatabaseService
import me.amitshekhar.newsapp.data.local.entity.LocalArticle
import me.amitshekhar.newsapp.data.model.toArticleEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OfflineArticleRepository @Inject constructor(
    private val networkService: NetworkService,
    private val databaseService: DatabaseService,
) {
    @OptIn(ExperimentalCoroutinesApi::class)
    fun getArticles(country: String): Flow<List<LocalArticle>> {
        return flow { emit(networkService.getTopHeadlines(country)) }
            .map {
                it.articles.map { article -> article.toArticleEntity() }
            }.flatMapConcat { articles ->
                flow { emit(databaseService.deleteAllAndInsertAll(articles)) }
            }.flatMapConcat {
                databaseService.getArticles()
            }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    fun fetchAndStoreArticlesInDB(country: String) {
        flow { emit(networkService.getTopHeadlines(country)) }
            .map {
                it.articles.map { article -> article.toArticleEntity() }
            }.flatMapConcat { articles ->
                flow { emit(databaseService.deleteAllAndInsertAll(articles)) }
            }.flatMapConcat {
                databaseService.getArticles()
            }
    }

    fun getArticlesDirectlyFromDB(): Flow<List<LocalArticle>> {
        return databaseService.getArticles()
    }
}
