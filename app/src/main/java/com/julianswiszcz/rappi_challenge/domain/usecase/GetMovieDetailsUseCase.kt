package com.julianswiszcz.rappi_challenge.domain.usecase

import com.julianswiszcz.rappi_challenge.domain.model.MovieDetails
import com.julianswiszcz.rappi_challenge.domain.repository.MovieRepositoryContract
import com.julianswiszcz.rappi_challenge.data.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(
    private val repository: MovieRepositoryContract
) {
    operator fun invoke(movieId: Int) : Flow<Resource<MovieDetails>> =
        repository.getMovieDetails(movieId)
}