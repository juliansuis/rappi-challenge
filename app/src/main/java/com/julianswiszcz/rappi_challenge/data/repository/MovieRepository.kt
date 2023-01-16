package com.julianswiszcz.rappi_challenge.data.repository

import com.julianswiszcz.rappi_challenge.domain.model.Movie
import com.julianswiszcz.rappi_challenge.domain.datasource.MovieDataSourceProvider
import com.julianswiszcz.rappi_challenge.data.database.MovieDatabase
import com.julianswiszcz.rappi_challenge.domain.model.MovieDetails
import com.julianswiszcz.rappi_challenge.data.util.Resource
import com.julianswiszcz.rappi_challenge.data.database.entities.MovieDetailsEntity
import com.julianswiszcz.rappi_challenge.data.database.entities.MovieEntity
import com.julianswiszcz.rappi_challenge.data.database.entities.MovieVideo
import com.julianswiszcz.rappi_challenge.data.database.entities.RecommendedMovies
import com.julianswiszcz.rappi_challenge.data.database.entities.TopRatedMovies
import com.julianswiszcz.rappi_challenge.data.database.entities.UpcomingMovies
import com.julianswiszcz.rappi_challenge.domain.repository.MovieRepositoryContract
import com.julianswiszcz.rappi_challenge.data.util.networkBoundResource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MovieRepository(
    private val db: MovieDatabase,
    private val dataSource: MovieDataSourceProvider,
) : MovieRepositoryContract {
    override fun getUpcomingMovies(): Flow<Resource<List<Movie>>> =
        networkBoundResource(
            {
                db.movieDao.getUpcomingMovies().map {
                    it.map { movie ->
                        Movie(movie.id, movie.image)
                    }
                }
            },
            { dataSource.fetchUpcomingMovies() },
            { r ->
                r.data?.results?.let {
                    db.movieDao.deleteUpcomingMovieIds()
                    db.movieDao.saveUpcomingMovieIds(
                        it.map { upcomingMovie ->
                            UpcomingMovies(upcomingMovie.id)
                        }
                    )
                    db.movieDao.saveAllMovies(
                        it.map { movie ->
                            MovieEntity(movie.id, movie.image)
                        }
                    )
                }
            }
        )

    override fun getTopRatedMovies(): Flow<Resource<List<Movie>>> =
        networkBoundResource(
            {
                db.movieDao.getTopRatedMovies().map {
                    it.map { movie ->
                        Movie(movie.id, movie.image)
                    }
                }
            },
            { dataSource.fetchTopRatedMovies() },
            { r ->
                r.data?.results?.let {
                    db.movieDao.deleteTopRatedMovieIds()
                    db.movieDao.saveTopRatedMovieIds(
                        it.map { movieId ->
                            TopRatedMovies(movieId.id)
                        }
                    )
                    db.movieDao.saveAllMovies(
                        it.map { movie ->
                            MovieEntity(movie.id, movie.image)
                        }
                    )
                }
            }
        )

    override fun getRecommendedMoviesByYear(year: Int): Flow<Resource<List<Movie>>> =
        networkBoundResource(
            {
                db.movieDao.getRecommendedMovies().map {
                    if (it.size > 5) {
                        it.subList(0, 6).map { movie ->
                            Movie(movie.id, movie.image)
                        }
                    } else {
                        it.map { movie ->
                            Movie(movie.id, movie.image)
                        }
                    }
                }
            },
            { dataSource.fetchRecommendedMoviesByYear(year) },
            { r ->
                r.data?.results?.let {
                    db.movieDao.deleteRecommendedMovieIds()
                    db.movieDao.saveRecommendedMovieIds(
                        it.map { movieId ->
                            RecommendedMovies(movieId.id)
                        }
                    )
                    db.movieDao.saveAllMovies(
                        it.map { movie ->
                            MovieEntity(movie.id, movie.image)
                        }
                    )
                }
            }
        )

    override fun getRecommendedMoviesByLanguage(language: String): Flow<Resource<List<Movie>>> =
        networkBoundResource(
            {
                db.movieDao.getRecommendedMovies().map {
                    if (it.size > 5) {
                        it.subList(0, 6).map { movie ->
                            Movie(movie.id, movie.image)
                        }
                    } else {
                        it.map { movie ->
                            Movie(movie.id, movie.image)
                        }
                    }
                }
            },
            { dataSource.fetchRecommendedMoviesByLanguage(language) },
            { r ->
                r.data?.results?.let {
                    db.movieDao.deleteRecommendedMovieIds()
                    db.movieDao.saveRecommendedMovieIds(
                        it.map { movieId ->
                            RecommendedMovies(movieId.id)
                        }
                    )
                    db.movieDao.saveAllMovies(
                        it.map { movie ->
                            MovieEntity(movie.id, movie.image)
                        }
                    )
                }
            }
        )

    override fun getMovieDetails(movieId: Int): Flow<Resource<MovieDetails>> =
        networkBoundResource(
            {
                db.movieDao.getMovieDetails().map {
                    if (it == null) {
                        MovieDetails(13, "Forrest Gump", null, "")
                    } else {
                        MovieDetails(it.id, it.name, it.image, it.description)
                    }
                }
            },
            { dataSource.fetchMovieDetails(movieId) },
            { r ->
                r.data?.let {
                    db.movieDao.deleteMovieDetails()
                    db.movieDao.saveMovieDetails(
                        MovieDetailsEntity(
                            it.id,
                            it.title,
                            it.image,
                            it.description
                        )
                    )
                }
            }
        )

    override fun getMovieVideos(movieId: Int): Flow<Resource<List<MovieVideo>>> =
        networkBoundResource(
            { db.movieDao.loadMovieVideo() },
            { dataSource.fetchMovieVideos(movieId) },
            {
                it.data?.results?.let { list ->
                    db.movieDao.deleteVideoIds()
                    db.movieDao.saveAllVideoIds(list)
                }
            }
        )
}
