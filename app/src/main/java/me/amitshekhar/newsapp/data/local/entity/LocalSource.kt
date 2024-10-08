package me.amitshekhar.newsapp.data.local.entity

import androidx.room.ColumnInfo

data class LocalSource(
    @ColumnInfo(name = "sourceId")
    val id: String?,
    @ColumnInfo(name = "sourceName")
    val name: String = ""
)
