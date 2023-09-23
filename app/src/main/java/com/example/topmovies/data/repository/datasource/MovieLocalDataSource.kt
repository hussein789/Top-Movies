package com.example.topmovies.data.repository.datasource

import com.example.topmovies.data.model.Genre
import com.example.topmovies.data.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieLocalDataSource {
    fun getMoviesFromDB(): Flow<List<Movie>>
    suspend fun saveMovies(movies: List<Movie>)
    suspend fun getGenreFromDB(): List<Genre>
    suspend fun saveGenres(genres: List<Genre>)
}
