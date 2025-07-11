package com.example.audiobookscomapplication.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.audiobookscomapplication.model.Podcast
import com.example.audiobookscomapplication.data.PodcastRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PodcastDetailsPageViewModel(val id: String?) : ViewModel(){

    var podcast: Podcast? by mutableStateOf(null)

    init{
        getPodcastData()
    }

    fun onFavorite() {
        if(podcast !== null) {
            podcast = podcast!!.copy(favorite = !podcast!!.favorite)
            CoroutineScope(Dispatchers.IO).launch {
                PodcastRepository.updateFavorite(podcast!!)
            }
        }
    }

    private fun getPodcastData() {
        id?.let { id ->
            podcast = PodcastRepository.podcastFlow.value.firstOrNull { it.id == id }
        }
    }
}