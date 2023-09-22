package com.example.topmovies.presentation.di

import com.example.topmovies.data.repository.MovieRepositoryImpl
import com.example.topmovies.data.repository.datasource.MovieLocalDataSource
import com.example.topmovies.data.repository.datasource.MovieRemoteDataSource
import com.example.topmovies.domain.repo.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    fun provideMovieRepository(
            movieLocalDataSource: MovieLocalDataSource,
            movieRemoteDataSource: MovieRemoteDataSource
            ): MovieRepository {
        return MovieRepositoryImpl(movieLocalDataSource, movieRemoteDataSource)
    }
}