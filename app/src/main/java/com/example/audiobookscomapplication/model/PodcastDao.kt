package com.practice.offlinecaching

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.audiobookscomapplication.model.Podcast

@Dao
interface PodcastDao
{
    @Query("select * from podcasts_table where id= :id")
    fun getPodcastFromId(id:String): Podcast

    @Query("SELECT * FROM podcasts_table")
    fun getPodcasts(): List<Podcast>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertPodcastsToRoom(podcasts:List<Podcast>)

    @Update
    suspend fun updatePodcast(podcast: Podcast)

}