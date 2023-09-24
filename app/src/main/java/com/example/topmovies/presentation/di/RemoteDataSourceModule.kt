package com.example.topmovies.presentation.di

import com.example.topmovies.data.remote.MoviesAPI
import com.example.topmovies.data.repository.datasource.MovieRemoteDataSource
import com.example.topmovies.data.repository.datasourceimpl.MovieRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RemoteDataSourceModule {

    @Provides
    fun provideMovieRemoteDataSource(moviesAPI: MoviesAPI): MovieRemoteDataSource {
        return MovieRemoteDataSourceImpl(moviesAPI)
    }
}
