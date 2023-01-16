package com.julianswiszcz.rappi_challenge.data.network

import com.julianswiszcz.rappi_challenge.BuildConfig
import com.julianswiszcz.rappi_challenge.data.model.MovieDetailsDTO
import com.julianswiszcz.rappi_challenge.data.model.RequestResponse
import com.julianswiszcz.rappi_challenge.data.model.RequestResponseVideo
import com.julianswiszcz.rappi_challenge.data.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieService @Inject constructor(private val api: APIService) {

    suspend fun getUpcomingMovies(): Resource<RequestResponse> =
        withContext(Dispatchers.IO) {
            val response = api.getUpcomingMovies(BuildConfig.API_KEY)
            if (response.isSuccessful) {
                Resource.success(response.body())
            } else {
                Resource.error(response.message(), response.body())
            }
        }

    suspend fun getTopRatedMovies(): Resource<RequestResponse> {
        return withContext(Dispatchers.IO) {
            val response = api.getTopRatedMovies(BuildConfig.API_KEY)
            if (response.isSuccessful) {
                Resource.success(response.body())
            } else {
                Resource.error(response.message(), response.body())
            }
        }
    }

    suspend fun getRecommendedMoviesByYear(year: Int): Resource<RequestResponse> {
        return withContext(Dispatchers.IO) {
            val response = api.getRecommendedMoviesByYear(BuildConfig.API_KEY, year)
            if (response.isSuccessful) {
                Resource.success(response.body())
            } else {
                Resource.error(response.message(), response.body())
            }
        }
    }

    suspend fun getRecommendedMoviesByLanguage(language: String): Resource<RequestResponse> =
        withContext(Dispatchers.IO) {
            val response = api.getRecommendedMoviesByLanguage(BuildConfig.API_KEY, language)
            if (response.isSuccessful) {
                Resource.success(response.body())
            } else {
                Resource.error(response.message(), response.body())
            }
        }

    suspend fun getMovieDetails(movieId: Int): Resource<MovieDetailsDTO> =
        withContext(Dispatchers.IO) {
            val response = api.getMovieDetails(movieId, BuildConfig.API_KEY)
            if (response.isSuccessful) {
                Resource.success(response.body())
            } else {
                Resource.error(response.message(), response.body())
            }
        }

    suspend fun getMovieVideos(movieId: Int): Resource<RequestResponseVideo> =
        withContext(Dispatchers.IO) {
            val response = api.getMovieVideos(movieId, BuildConfig.API_KEY)
            if (response.isSuccessful) {
                Resource.success(response.body())
            } else {
                Resource.error(response.message(), response.body())
            }
        }
}
