package com.julianswiszcz.rappi_challenge.domain.usecase

import javax.inject.Inject

data class MovieUseCases @Inject constructor(
    val upcomingMovies: GetUpcomingMoviesUseCase,
    val topRatedMovies: GetTopRatedMoviesUseCase,
    val recommendedMoviesByYear: GetRecommendedMoviesByYearUseCase,
    val recommendedMoviesByLanguage: GetRecommendedMoviesByLanguageUseCase,
    val movieDetails: GetMovieDetailsUseCase,
    val movieVideos: GetMovieVideosUseCase,
)