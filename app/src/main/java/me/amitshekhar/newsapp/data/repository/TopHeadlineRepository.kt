package me.amitshekhar.newsapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import me.amitshekhar.newsapp.data.api.NetworkService
import me.amitshekhar.newsapp.data.model.Article
import me.amitshekhar.newsapp.utils.AppConstant
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
            it.articles
        }
    }

    fun getTopHeadlinesByKeyword(keyword: String): Flow<List<Article>> {
        return flow {
            emit(networkService.getTopHeadlinesByKeyword(keyword))
        }.map {
            it.articles
        }
    }

    fun getTopHeadlinesWithPaging(): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(
                pageSize = AppConstant.PAGE_SIZE
            ),
            pagingSourceFactory = {
                TopHeadlinePagingSource(networkService)
            }
        ).flow
    }
}
