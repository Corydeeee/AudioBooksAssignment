package com.example.audiobookscomapplication.model

data class Podcast (
    val id: String?,
    val title: String?,
    val image: String?,
    val thumbnail: String?,
    val description: String?,
    val publisher: String?,
) {
    companion object {
        fun stub(
            id: String? = "1",
            title: String? = "Podcast Title",
            image: String? = "",
            thumbnail: String? = "",
            description: String? = "This is a test description",
            publisher: String? = "NPR"
        ) : Podcast = Podcast(
            id,
            title,
            image,
            thumbnail,
            description,
            publisher
        )
    }
}