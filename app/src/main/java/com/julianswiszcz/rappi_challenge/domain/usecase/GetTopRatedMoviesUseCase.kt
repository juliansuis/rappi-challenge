package com.julianswiszcz.rappi_challenge.domain.usecase

import com.julianswiszcz.rappi_challenge.domain.model.Movie
import com.julianswiszcz.rappi_challenge.domain.repository.MovieRepositoryContract
import com.julianswiszcz.rappi_challenge.data.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTopRatedMoviesUseCase @Inject constructor(
    private val repository: MovieRepositoryContract
) {
    operator fun invoke() : Flow<Resource<List<Movie>>> =
        repository.getTopRatedMovies()
}
