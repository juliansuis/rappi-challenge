package com.julianswiszcz.rappi_challenge.domain.datasource

import com.julianswiszcz.rappi_challenge.data.model.RequestResponse
import com.julianswiszcz.rappi_challenge.data.model.RequestResponseVideo
import com.julianswiszcz.rappi_challenge.data.model.MovieDetailsDTO
import com.julianswiszcz.rappi_challenge.data.util.Resource

interface MovieDataSourceProvider  {
    suspend fun fetchUpcomingMovies(): Resource<RequestResponse>
    suspend fun fetchTopRatedMovies(): Resource<RequestResponse>
    suspend fun fetchRecommendedMoviesByYear(year: Int): Resource<RequestResponse>
    suspend fun fetchRecommendedMoviesByLanguage(language: String): Resource<RequestResponse>
    suspend fun fetchMovieDetails(movieId: Int): Resource<MovieDetailsDTO>
    suspend fun fetchMovieVideos(movieId: Int): Resource<RequestResponseVideo>
}
