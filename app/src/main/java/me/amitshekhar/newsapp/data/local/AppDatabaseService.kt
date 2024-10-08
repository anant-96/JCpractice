package me.amitshekhar.newsapp.data.local

import kotlinx.coroutines.flow.Flow
import me.amitshekhar.newsapp.data.local.entity.LocalArticle

class AppDatabaseService constructor(private val appDatabase: AppDatabase) : DatabaseService {

    override fun getArticles(): Flow<List<LocalArticle>> {
        return appDatabase.articleDao().getAll()
    }

    override fun deleteAllAndInsertAll(articles: List<LocalArticle>) {
        return appDatabase.articleDao().deleteAllAndInsertAll(articles)
    }
}
