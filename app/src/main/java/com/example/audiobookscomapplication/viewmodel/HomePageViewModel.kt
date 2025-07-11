package com.example.audiobookscomapplication.viewmodel

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope

import com.example.audiobookscomapplication.model.Podcast
import com.example.audiobookscomapplication.model.PodcastRepository
import kotlinx.coroutines.launch

class HomePageViewModel (application: Application) : AndroidViewModel(application) {

    var isLoading by mutableStateOf(true)
    private var pageCount by mutableIntStateOf(1)
    var shownData by mutableStateOf<List<Podcast>?>(null)
    var totalCount by mutableIntStateOf(1)

    var podcastData: List<Podcast> by mutableStateOf(emptyList())

    init {
        viewModelScope.launch {
            PodcastRepository.podcastFlow.collect { data ->
                if(data.isNotEmpty() && isLoading) {
                    isLoading = false
                }
                podcastData = data
                totalCount = podcastData.size
                updateShownData()
                // Process the emitted data
                println("Received data from singleton: $data")
            }
        }
    }

    fun nextPage()  {
        if(shownData != podcastData){
            pageCount ++
            updateShownData()
        }
    }

    private fun updateShownData(){
        if(podcastData.isNotEmpty()) {
            shownData = podcastData.subList(0, pageCount * 10)
        }
    }

}