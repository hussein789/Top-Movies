package com.example.topmovies.data.repository.datasource

import com.example.topmovies.data.model.Genre
import com.example.topmovies.data.model.Movie

interface MovieRemoteDataSource {
    suspend fun getMoviesFromRemote(): List<Movie>
    suspend fun getGenresFromRemote(): List<Genre>
}
