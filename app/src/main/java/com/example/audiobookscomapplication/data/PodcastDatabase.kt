import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.audiobookscomapplication.model.Podcast
import com.practice.offlinecaching.PodcastDao

@Database(entities = [Podcast::class], version = 1)
abstract class PodcastDatabase : RoomDatabase() {
    abstract fun podcastDao(): PodcastDao
}