package com.example.audiobookscomapplication.model

import PodcastDatabase
import android.content.Context
import android.util.Log
import androidx.room.Room
import com.example.audiobookscomapplication.services.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
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
        fetchPodcastsFromDb()
    }
    private fun fetchPodcastsFromDb(){
        _podcastFlow.value = db.podcastDao().getPodcasts()
    }

    fun getPodcast(id: String?): Podcast?{
        id?.let {
            return db.podcastDao().getPodcastFromId(id)
        }
        return null
    }

    suspend fun updateFavorite(podcast: Podcast) {
        db.podcastDao().updatePodcast(podcast)
        fetchPodcastsFromDb()
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