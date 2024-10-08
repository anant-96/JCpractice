package me.amitshekhar.newsapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow
import me.amitshekhar.newsapp.data.local.entity.LocalArticle

@Dao
interface ArticleDao {

    @Query("SELECT * FROM article")
    fun getAll(): Flow<List<LocalArticle>>

    @Insert
    fun insertAll(articles: List<LocalArticle>)

    @Query("DELETE FROM article")
    fun deleteAll()

    @Transaction
    fun deleteAllAndInsertAll(articles: List<LocalArticle>) {
        deleteAll()
        return insertAll(articles)
    }

}
