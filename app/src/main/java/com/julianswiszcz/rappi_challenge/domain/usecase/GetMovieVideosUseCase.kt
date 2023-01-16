package com.julianswiszcz.rappi_challenge.domain.usecase

import com.julianswiszcz.rappi_challenge.domain.repository.MovieRepositoryContract
import com.julianswiszcz.rappi_challenge.data.database.entities.MovieVideo
import com.julianswiszcz.rappi_challenge.data.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMovieVideosUseCase @Inject constructor(
    private val repository: MovieRepositoryContract
) {
    operator fun invoke(movieId: Int) : Flow<Resource<List<MovieVideo>>> =
        repository.getMovieVideos(movieId)
}
