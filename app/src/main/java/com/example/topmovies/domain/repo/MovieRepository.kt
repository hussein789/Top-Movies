package com.example.topmovies.domain.repo

import androidx.lifecycle.LiveData
import com.example.topmovies.data.model.Movie

interface MovieRepository {
    fun getMovies(): LiveData<List<Movie>>
    suspend fun updateMovies()
}
