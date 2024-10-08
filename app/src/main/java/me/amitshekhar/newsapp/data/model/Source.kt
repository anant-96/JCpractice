package me.amitshekhar.newsapp.data.model

import com.google.gson.annotations.SerializedName
import me.amitshekhar.newsapp.data.local.entity.LocalSource

data class Source(
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("name")
    val name: String = "",
    @SerializedName("url")
    val url: String = "",
    @SerializedName("description")
    val description: String = "",
)

fun Source.toSourceEntity(): LocalSource {
    return LocalSource(id, name)
}
