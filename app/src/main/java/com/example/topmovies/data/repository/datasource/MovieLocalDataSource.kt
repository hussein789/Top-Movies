package com.example.topmovies.data.repository.datasource

import androidx.lifecycle.LiveData
import com.example.topmovies.data.model.Movie

interface MovieLocalDataSource {
    fun getMoviesFromDB(): LiveData<List<Movie>>
    suspend fun saveMovies(movies: List<Movie>)
}
