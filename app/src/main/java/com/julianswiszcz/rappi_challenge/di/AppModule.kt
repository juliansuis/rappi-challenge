package com.julianswiszcz.rappi_challenge.di

import android.app.Application
import androidx.room.Room
import com.julianswiszcz.rappi_challenge.data.network.APIService
import com.julianswiszcz.rappi_challenge.domain.usecase.GetMovieDetailsUseCase
import com.julianswiszcz.rappi_challenge.domain.usecase.GetMovieVideosUseCase
import com.julianswiszcz.rappi_challenge.domain.usecase.GetRecommendedMoviesByLanguageUseCase
import com.julianswiszcz.rappi_challenge.domain.usecase.GetRecommendedMoviesByYearUseCase
import com.julianswiszcz.rappi_challenge.domain.usecase.GetTopRatedMoviesUseCase
import com.julianswiszcz.rappi_challenge.domain.usecase.GetUpcomingMoviesUseCase
import com.julianswiszcz.rappi_challenge.data.database.dao.MovieDao
import com.julianswiszcz.rappi_challenge.data.datasource.MovieDataSource
import com.julianswiszcz.rappi_challenge.domain.datasource.MovieDataSourceProvider
import com.julianswiszcz.rappi_challenge.data.database.MovieDatabase
import com.julianswiszcz.rappi_challenge.data.repository.MovieRepository
import com.julianswiszcz.rappi_challenge.domain.repository.MovieRepositoryContract
import com.julianswiszcz.rappi_challenge.data.network.MovieService
import com.julianswiszcz.rappi_challenge.domain.usecase.MovieUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMovieDatabase(app: Application): MovieDatabase =
        Room.databaseBuilder(
            app,
            MovieDatabase::class.java,
            MovieDatabase.DATABASE_NAME
        ).build()

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideAPIService(retrofit: Retrofit): APIService =
        retrofit.create(APIService::class.java)

    @Provides
    @Singleton
    fun provideMovieService(api: APIService) = MovieService(api)

    @Provides
    @Singleton
    fun provideMovieDao(db: MovieDatabase): MovieDao = db.movieDao

    @Provides
    @Singleton
    fun provideMovieDataSource(api: MovieService): MovieDataSourceProvider =
        MovieDataSource(api)

    @Provides
    @Singleton
    fun provideMovieRepository(
        db: MovieDatabase,
        dataSource: MovieDataSourceProvider
    ): MovieRepositoryContract =
        MovieRepository(db, dataSource)

    @Provides
    @Singleton
    fun provideMovieUseCases(
        repository: MovieRepositoryContract
    ): MovieUseCases = MovieUseCases(
            upcomingMovies = GetUpcomingMoviesUseCase(repository),
            topRatedMovies = GetTopRatedMoviesUseCase(repository),
            recommendedMoviesByYear = GetRecommendedMoviesByYearUseCase(repository),
            recommendedMoviesByLanguage = GetRecommendedMoviesByLanguageUseCase(repository),
            movieDetails = GetMovieDetailsUseCase(repository),
            movieVideos = GetMovieVideosUseCase(repository)
        )
}
