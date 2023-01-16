package com.julianswiszcz.rappi_challenge.data.datasource

import com.julianswiszcz.rappi_challenge.domain.datasource.MovieDataSourceProvider
import com.julianswiszcz.rappi_challenge.data.model.RequestResponse
import com.julianswiszcz.rappi_challenge.data.model.RequestResponseVideo
import com.julianswiszcz.rappi_challenge.data.model.MovieDetailsDTO
import com.julianswiszcz.rappi_challenge.data.network.MovieService
import com.julianswiszcz.rappi_challenge.data.util.Resource
import javax.inject.Inject

class MovieDataSource @Inject constructor(
    private val api: MovieService,
) : MovieDataSourceProvider {
    override suspend fun fetchUpcomingMovies(): Resource<RequestResponse> =
        api.getUpcomingMovies()

    override suspend fun fetchTopRatedMovies(): Resource<RequestResponse> =
        api.getTopRatedMovies()

    override suspend fun fetchRecommendedMoviesByYear(year: Int): Resource<RequestResponse> =
        api.getRecommendedMoviesByYear(year)

    override suspend fun fetchRecommendedMoviesByLanguage(
        language: String
    ): Resource<RequestResponse> = api.getRecommendedMoviesByLanguage(language)

    override suspend fun fetchMovieDetails(movieId: Int): Resource<MovieDetailsDTO> =
        api.getMovieDetails(movieId)

    override suspend fun fetchMovieVideos(movieId: Int): Resource<RequestResponseVideo> =
        api.getMovieVideos(movieId)
}
