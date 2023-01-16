package com.julianswiszcz.rappi_challenge.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.julianswiszcz.rappi_challenge.data.database.dao.MovieDao
import com.julianswiszcz.rappi_challenge.data.database.entities.MovieDetailsEntity
import com.julianswiszcz.rappi_challenge.data.database.entities.MovieEntity
import com.julianswiszcz.rappi_challenge.data.database.entities.MovieVideo
import com.julianswiszcz.rappi_challenge.data.database.entities.RecommendedMovies
import com.julianswiszcz.rappi_challenge.data.database.entities.TopRatedMovies
import com.julianswiszcz.rappi_challenge.data.database.entities.UpcomingMovies

@Database(
    entities = [MovieEntity::class, MovieDetailsEntity::class, MovieVideo::class,
        UpcomingMovies::class, TopRatedMovies::class, RecommendedMovies::class],
    version = 1
)
abstract class MovieDatabase : RoomDatabase() {
    abstract val movieDao: MovieDao

    companion object {
        const val DATABASE_NAME: String = "movie_database"
    }
}