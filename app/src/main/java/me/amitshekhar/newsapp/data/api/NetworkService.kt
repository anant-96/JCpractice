package me.amitshekhar.newsapp.data.api

import me.amitshekhar.newsapp.data.model.NewsSourcesResponse
import me.amitshekhar.newsapp.data.model.TopHeadlinesResponse
import me.amitshekhar.newsapp.utils.AppConstant.API_KEY
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface NetworkService {

    @Headers("X-Api-Key: $API_KEY")
    @GET("top-headlines")
    suspend fun getTopHeadlines(@Query("country") country: String): TopHeadlinesResponse

    @Headers("X-Api-Key: $API_KEY")
    @GET("top-headlines")
    suspend fun getTopHeadlines(
        @Query("country") country: String,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int
    ): TopHeadlinesResponse

    @Headers("X-api-key: $API_KEY")
    @GET("top-headlines")
    suspend fun getTopHeadlinesByLanguage(@Query("language") language: String): TopHeadlinesResponse

    @Headers("X-Api-Key: $API_KEY")
    @GET("everything")
    suspend fun getTopHeadlinesByKeyword(@Query("q") query: String): TopHeadlinesResponse

    @Headers("X-Api-Key: $API_KEY")
    @GET("top-headlines/sources")
    suspend fun getNewsSource(): NewsSourcesResponse
}
