package com.example.audiobookscomapplication.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="podcasts_table")
data class Podcast (
    @PrimaryKey(autoGenerate = false)
    val id: String,
    @ColumnInfo(name="title")
    val title: String?,
    @ColumnInfo(name="image")
    val image: String?,
    @ColumnInfo(name="thumbnail")
    val thumbnail: String?,
    @ColumnInfo(name="description")
    val description: String?,
    @ColumnInfo(name="publisher")
    val publisher: String?,
    @ColumnInfo(name="favorite")
    var favorite: Boolean = false
) {
    companion object {
        fun stub(
            id: String = "1",
            title: String? = "Podcast Title",
            image: String? = "",
            thumbnail: String? = "",
            description: String? = "This is a test description",
            publisher: String? = "NPR",
            favorite: Boolean = false
        ) : Podcast = Podcast(
            id,
            title,
            image,
            thumbnail,
            description,
            publisher,
            favorite

        )
    }
}