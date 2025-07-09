package com.example.audiobookscomapplication.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.audiobookscomapplication.model.Podcast
import com.example.audiobookscomapplication.model.PodcastRepository
import kotlinx.coroutines.launch

class HomeViewModel () : ViewModel() {

    private var podcastData by mutableStateOf<List<Podcast>?>(null)
    var isLoading by mutableStateOf(true)
    private var pageCount by mutableIntStateOf(1)
    var shownData by mutableStateOf<List<Podcast>?>(null)
    var totalCount by mutableIntStateOf(1)
    init {
        getPodcastData()
    }

    fun nextPage()  {
        if(shownData != podcastData){
            pageCount ++
            updateShownData()
        }
    }

    private fun updateShownData(){
        shownData = podcastData?.subList(0, pageCount * 10)
    }

    private fun getPodcastData() {
        viewModelScope.launch {
            val podcastRepository = PodcastRepository()
            val podcastResult = podcastRepository.fetchPodcastData()
            podcastData = podcastRepository.podcasts
            totalCount = podcastData?.size ?: 0
            updateShownData()
            isLoading = false

        }

    }
}