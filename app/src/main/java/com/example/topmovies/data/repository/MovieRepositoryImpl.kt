package com.example.topmovies.data.repository

import androidx.lifecycle.LiveData
import com.example.topmovies.data.model.Movie
import com.example.topmovies.data.repository.datasource.MovieLocalDataSource
import com.example.topmovies.data.repository.datasource.MovieRemoteDataSource
import com.example.topmovies.domain.repo.MovieRepository

class MovieRepositoryImpl(
        private val movieLocalDataSource: MovieLocalDataSource,
        private val movieRemoteDataSource: MovieRemoteDataSource,
) : MovieRepository {

    override fun getMovies(): LiveData<List<Movie>> {
        return movieLocalDataSource.getMoviesFromDB()
    }

    override suspend fun updateMovies() {
        val moviesList = movieRemoteDataSource.getMoviesFromRemote()
        movieLocalDataSource.saveMovies(moviesList)
    }
}
