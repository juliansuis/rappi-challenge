package com.julianswiszcz.rappi_challenge.domain.repository

import com.julianswiszcz.rappi_challenge.domain.model.Movie
import com.julianswiszcz.rappi_challenge.domain.model.MovieDetails
import com.julianswiszcz.rappi_challenge.data.util.Resource
import com.julianswiszcz.rappi_challenge.data.database.entities.MovieVideo
import kotlinx.coroutines.flow.Flow

interface MovieRepositoryContract {
    fun getUpcomingMovies(): Flow<Resource<List<Movie>>>
    fun getTopRatedMovies(): Flow<Resource<List<Movie>>>
    fun getRecommendedMoviesByYear(year: Int): Flow<Resource<List<Movie>>>
    fun getRecommendedMoviesByLanguage(language: String): Flow<Resource<List<Movie>>>
    fun getMovieDetails(movieId: Int): Flow<Resource<MovieDetails>>
    fun getMovieVideos(movieId: Int): Flow<Resource<List<MovieVideo>>>
}