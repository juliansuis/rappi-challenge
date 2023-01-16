package com.julianswiszcz.rappi_challenge.data.network

import com.julianswiszcz.rappi_challenge.data.model.MovieDetailsDTO
import com.julianswiszcz.rappi_challenge.data.model.RequestResponse
import com.julianswiszcz.rappi_challenge.data.model.RequestResponseVideo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface APIService {
    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(@Query("api_key") api_key: String): Response<RequestResponse>

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(@Query("api_key") api_key: String): Response<RequestResponse>

    @GET("movie/top_rated")
    suspend fun getRecommendedMoviesByYear(
        @Query("api_key") api_key: String,
        @Query("year") year: Int,
    ): Response<RequestResponse>

    @GET("movie/top_rated")
    suspend fun getRecommendedMoviesByLanguage(
        @Query("api_key") api_key: String,
        @Query("language") language: String,
    ): Response<RequestResponse>

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Int,
        @Query("api_key") api_key: String,
    ): Response<MovieDetailsDTO>

    @GET("movie/{movie_id}/videos")
    suspend fun getMovieVideos(
        @Path("movie_id") movieId: Int,
        @Query("api_key") api_key: String
    ): Response<RequestResponseVideo>
}
