package com.example.audiobookscomapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.audiobookscomapplication.model.Podcast
import com.example.audiobookscomapplication.model.PodcastRepository
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val podcastRepository: PodcastRepository = PodcastRepository()

    private val _podcastData = MutableLiveData<List<Podcast>?>()
    val podcastData : LiveData<List<Podcast>?> = _podcastData

    fun getPodcastData() {
        viewModelScope.launch {
            val podcastResult = podcastRepository.fetchPodcastData()
            _podcastData.postValue(podcastResult?.podcasts)
        }
    }
}