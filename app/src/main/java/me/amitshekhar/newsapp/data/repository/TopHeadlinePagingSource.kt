package me.amitshekhar.newsapp.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import me.amitshekhar.newsapp.data.api.NetworkService
import me.amitshekhar.newsapp.data.model.Article
import me.amitshekhar.newsapp.utils.AppConstant
import me.amitshekhar.newsapp.utils.AppConstant.INITIAL_PAGE
import me.amitshekhar.newsapp.utils.AppConstant.PAGE_SIZE

class TopHeadlinePagingSource(private val networkService: NetworkService) :
    PagingSource<Int, Article>() {
    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        return try {
            val page = params.key ?: INITIAL_PAGE

            val response = networkService.getTopHeadlines(
                country = AppConstant.COUNTRY, page = page, pageSize = PAGE_SIZE
            )

            LoadResult.Page(
                data = response.articles,
                prevKey = if (page == INITIAL_PAGE) null else page.minus(1),
                nextKey = if (response.articles.isEmpty()) null else page.plus(1),
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

}
