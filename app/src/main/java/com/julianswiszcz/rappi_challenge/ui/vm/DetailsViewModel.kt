package com.julianswiszcz.rappi_challenge.ui.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.switchMap
import com.julianswiszcz.rappi_challenge.domain.model.MovieDetails
import com.julianswiszcz.rappi_challenge.domain.usecase.MovieUseCases
import com.julianswiszcz.rappi_challenge.data.database.entities.MovieVideo
import com.julianswiszcz.rappi_challenge.data.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val useCases: MovieUseCases
) : ViewModel() {

    val movieId: MutableLiveData<Int> = MutableLiveData()

    val movieDetails: LiveData<Resource<MovieDetails>> = movieId.switchMap {
        useCases.movieDetails(it).asLiveData()
    }

    val movieVideos: LiveData<Resource<List<MovieVideo>>> = movieId.switchMap {
        useCases.movieVideos(it).asLiveData()
    }

    fun setMovieId(id: Int) {
        movieId.value = id
    }
}
