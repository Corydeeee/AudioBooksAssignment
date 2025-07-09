package com.example.audiobookscomapplication.model

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.audiobookscomapplication.services.RetrofitInstance

class PodcastRepository {
    var podcasts by mutableStateOf<List<Podcast>?>(null)

    suspend fun fetchPodcastData() {
        try {
            val podcastResponse = RetrofitInstance.api.getPodcasts()
            podcasts =  podcastResponse.podcasts
        } catch (e: Exception) {
            Log.e("ERROR", e.message.toString())
        }
    }
}