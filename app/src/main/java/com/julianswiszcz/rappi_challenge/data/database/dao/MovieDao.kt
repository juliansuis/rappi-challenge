package com.julianswiszcz.rappi_challenge.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.julianswiszcz.rappi_challenge.data.database.entities.MovieDetailsEntity
import com.julianswiszcz.rappi_challenge.data.database.entities.MovieEntity
import com.julianswiszcz.rappi_challenge.data.database.entities.MovieVideo
import com.julianswiszcz.rappi_challenge.data.database.entities.RecommendedMovies
import com.julianswiszcz.rappi_challenge.data.database.entities.TopRatedMovies
import com.julianswiszcz.rappi_challenge.data.database.entities.UpcomingMovies
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie_table INNER JOIN upcoming_movies ON movie_table.id = upcoming_movies.id")
    fun getUpcomingMovies(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movie_table INNER JOIN top_rated_movies ON movie_table.id = top_rated_movies.id")
    fun getTopRatedMovies(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movie_table INNER JOIN recommended_movies ON movie_table.id = recommended_movies.id")
    fun getRecommendedMovies(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movie_details")
    fun getMovieDetails(): Flow<MovieDetailsEntity?>

    @Query("SELECT * FROM movie_video")
    fun loadMovieVideo(): Flow<List<MovieVideo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAllVideoIds(VideoIds: List<MovieVideo>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAllMovies(movies: List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveUpcomingMovieIds(movieIds: List<UpcomingMovies>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveTopRatedMovieIds(movieIds: List<TopRatedMovies>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveRecommendedMovieIds(movieIds: List<RecommendedMovies>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveMovieDetails(movie: MovieDetailsEntity)

    // @Query("DELETE FROM movie_table INNER JOIN upcoming_movies ON movie_table.id = upcoming_movies.id")
    // fun deleteUpcomingMovies()

    @Query("DELETE FROM movie_details")
    fun deleteMovieDetails()

    @Query("DELETE FROM upcoming_movies")
    fun deleteUpcomingMovieIds()

    @Query("DELETE FROM top_rated_movies")
    fun deleteTopRatedMovieIds()

    @Query("DELETE FROM recommended_movies")
    fun deleteRecommendedMovieIds()

    @Query("DELETE FROM movie_video")
    fun deleteVideoIds()
}
