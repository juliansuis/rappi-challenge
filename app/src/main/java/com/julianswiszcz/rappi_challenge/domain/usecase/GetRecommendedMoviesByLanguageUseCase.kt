package com.julianswiszcz.rappi_challenge.domain.usecase

import com.julianswiszcz.rappi_challenge.domain.model.Movie
import com.julianswiszcz.rappi_challenge.domain.repository.MovieRepositoryContract
import com.julianswiszcz.rappi_challenge.data.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRecommendedMoviesByLanguageUseCase @Inject constructor(
    private val repository: MovieRepositoryContract
) {
    operator fun invoke(language: String) : Flow<Resource<List<Movie>>> =
        repository.getRecommendedMoviesByLanguage(language)
}
