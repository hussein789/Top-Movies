package com.example.topmovies.data.repository.datasourceimpl

import androidx.lifecycle.LiveData
import com.example.topmovies.data.db.MovieDao
import com.example.topmovies.data.model.Movie
import com.example.topmovies.data.repository.datasource.MovieLocalDataSource

class MovieLocalDataSourceImpl(
        private val movieDao: MovieDao,
) : MovieLocalDataSource {
    override fun getMoviesFromDB(): LiveData<List<Movie>> {
        return movieDao.getAllMovies()
    }

    override suspend fun saveMovies(movies: List<Movie>) {
        movieDao.saveMovies(movies)
    }
}
