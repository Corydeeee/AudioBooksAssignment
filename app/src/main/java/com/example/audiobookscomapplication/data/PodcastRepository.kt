package com.example.audiobookscomapplication.data

import PodcastDatabase
import android.content.Context
import android.util.Log
import androidx.room.Room
import com.example.audiobookscomapplication.model.Podcast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext

object PodcastRepository {

    private lateinit var db: PodcastDatabase
    private val _podcastFlow = MutableStateFlow<List<Podcast>>(emptyList())
    val podcastFlow: StateFlow<List<Podcast>> = _podcastFlow

    suspend fun init(context: Context){
        db = Room.databaseBuilder(
            context,
            PodcastDatabase::class.java, "podcasts_table"
        ).build()
        fetchPodcastData()
        updatePodcastsFromDb()
    }
    private fun updatePodcastsFromDb(){
        _podcastFlow.value = db.podcastDao().getPodcasts()
    }

    suspend fun updateFavorite(podcast: Podcast) {
        db.podcastDao().updatePodcast(podcast)
        updatePodcastsFromDb()
    }

    suspend fun fetchPodcastData() {
        withContext(Dispatchers.IO){
            try {
                val podcastResponse = RetrofitInstance.api.getPodcasts()
                podcastResponse.podcasts?.let{
                    db.podcastDao().insertPodcastsToRoom(it)
                }
            } catch (e: Exception) {
                Log.e("ERROR", e.message.toString())
            }
        }
    }
}