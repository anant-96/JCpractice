package me.amitshekhar.newsapp.data.local

import kotlinx.coroutines.flow.Flow
import me.amitshekhar.newsapp.data.local.entity.LocalArticle

interface DatabaseService {

    fun getArticles(): Flow<List<LocalArticle>>

    fun deleteAllAndInsertAll(articles: List<LocalArticle>)
}
