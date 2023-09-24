package com.example.topmovies.presentation.di

import com.example.topmovies.data.db.GenreDao
import com.example.topmovies.data.db.MovieDao
import com.example.topmovies.data.repository.datasource.MovieLocalDataSource
import com.example.topmovies.data.repository.datasourceimpl.MovieLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class LocalDataSourceModule {

    @Provides
    fun provideMovieLocalDataSource(movieDao: MovieDao, genreDao: GenreDao): MovieLocalDataSource {
        return MovieLocalDataSourceImpl(movieDao, genreDao)
    }
}
