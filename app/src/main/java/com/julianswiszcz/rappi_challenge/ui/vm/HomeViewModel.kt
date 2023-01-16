package com.julianswiszcz.rappi_challenge.ui.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.switchMap
import com.julianswiszcz.rappi_challenge.domain.model.Movie
import com.julianswiszcz.rappi_challenge.domain.usecase.MovieUseCases
import com.julianswiszcz.rappi_challenge.data.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCases: MovieUseCases
) : ViewModel() {

    val filterYear: MutableLiveData<Boolean> = MutableLiveData(false)
    val filterLanguage: MutableLiveData<Boolean> = MutableLiveData(true)

    val upcomingMovies: LiveData<Resource<List<Movie>>> =
        useCases.upcomingMovies().asLiveData()

    val topRatedMovies: LiveData<Resource<List<Movie>>> =
        useCases.topRatedMovies().asLiveData()

    val recommendedMovies: LiveData<Resource<List<Movie>>> = filterYear.switchMap {
        if (it) {
            useCases.recommendedMoviesByYear(1993).asLiveData()
        } else {
            useCases.recommendedMoviesByLanguage("es-ES").asLiveData()
        }
    }

    fun setFilterYear() {
        filterYear.value = true
        filterLanguage.value = false
    }

    fun setFilterLanguage() {
        filterYear.value = false
        filterLanguage.value = true
    }
}
