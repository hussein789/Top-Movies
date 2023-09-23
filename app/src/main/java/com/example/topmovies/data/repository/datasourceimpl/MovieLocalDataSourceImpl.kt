package com.example.topmovies.data.repository.datasourceimpl

import androidx.lifecycle.LiveData
import com.example.topmovies.data.db.GenreDao
import com.example.topmovies.data.db.MovieDao
import com.example.topmovies.data.model.Genre
import com.example.topmovies.data.model.Movie
import com.example.topmovies.data.repository.datasource.MovieLocalDataSource
import kotlinx.coroutines.flow.Flow

class MovieLocalDataSourceImpl(
        private val movieDao: MovieDao,
        private val genreDao: GenreDao
) : MovieLocalDataSource {
    override fun getMoviesFromDB(): Flow<List<Movie>> {
        return movieDao.getAllMovies()
    }

    override suspend fun saveMovies(movies: List<Movie>) {
        movieDao.saveMovies(movies)
    }

    override suspend fun getGenreFromDB(): List<Genre> {
        return genreDao.getAllGenre()
    }

    override suspend fun saveGenres(genres: List<Genre>) {
        genreDao.insertGenres(genres)
    }
}
