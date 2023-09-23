package com.example.topmovies.data.repository

import com.example.topmovies.data.model.Genre
import com.example.topmovies.data.model.Movie
import com.example.topmovies.data.repository.datasource.MovieLocalDataSource
import com.example.topmovies.data.repository.datasource.MovieRemoteDataSource
import com.example.topmovies.domain.repo.MovieRepository
import kotlinx.coroutines.flow.Flow

class MovieRepositoryImpl(
        private val movieLocalDataSource: MovieLocalDataSource,
        private val movieRemoteDataSource: MovieRemoteDataSource,
) : MovieRepository {

    override fun getMovies(): Flow<List<Movie>> {
        return movieLocalDataSource.getMoviesFromDB()
    }

    override suspend fun updateMovies() {
        val moviesList = movieRemoteDataSource.getMoviesFromRemote()
        movieLocalDataSource.saveMovies(moviesList)
    }

    override suspend fun getGenres(): List<Genre> {
        return movieLocalDataSource.getGenreFromDB()
    }

    override suspend fun updateGenres() {
        val remoteGenres = movieRemoteDataSource.getGenresFromRemote()
        movieLocalDataSource.saveGenres(remoteGenres)

    }
}
