package com.example.topmovies.domain.repo

import com.example.topmovies.data.model.Genre
import com.example.topmovies.data.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getMovies(): Flow<List<Movie>>
    suspend fun updateMovies()
    suspend fun getGenres(): List<Genre>
    suspend fun updateGenres()
}
