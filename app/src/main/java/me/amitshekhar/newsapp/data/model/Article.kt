package me.amitshekhar.newsapp.data.model

import com.google.gson.annotations.SerializedName
import me.amitshekhar.newsapp.data.local.entity.LocalArticle

data class Article(
    @SerializedName("title")
    val title: String = "",
    @SerializedName("description")
    val description: String = "",
    @SerializedName("url")
    val url: String = "",
    @SerializedName("urlToImage")
    val imageUrl: String = "",
    @SerializedName("source")
    val source: Source
)

fun Article.toArticleEntity(): LocalArticle {
    return LocalArticle(
        title = title,
        description = description,
        url = url,
        imageUrl = imageUrl,
        source = source.toSourceEntity()
    )
}
