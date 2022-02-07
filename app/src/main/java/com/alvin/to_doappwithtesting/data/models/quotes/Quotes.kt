package com.alvin.to_doappwithtesting.data.models.quotes

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Quotes")
data class Quotes(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "text") val text: String = "",
    @ColumnInfo(name = "author") var author: String = "",
    @ColumnInfo(name = "is_liked") val isLiked: Boolean = false,
    @ColumnInfo(name = "likeCount") val likeCount: Int = 0
)
