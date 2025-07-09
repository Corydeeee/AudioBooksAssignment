package com.example.audiobookscomapplication.services

import com.example.audiobookscomapplication.model.PodcastResponse
import retrofit2.http.GET

// Interface for defining the API endpoints.
interface ApiService {
    // Endpoint to fetch a list of podcasts.
    @GET("best_podcasts")
    suspend fun getPodcasts(): PodcastResponse
}