package com.example.topmovies.data.repository.datasourceimpl

import com.example.topmovies.BuildConfig
import com.example.topmovies.data.model.Genre
import com.example.topmovies.data.model.Movie
import com.example.topmovies.data.remote.MoviesAPI
import com.example.topmovies.data.repository.datasource.MovieRemoteDataSource

class MovieRemoteDataSourceImpl(
        private val moviesAPI: MoviesAPI,
) : MovieRemoteDataSource {
    override suspend fun getMoviesFromRemote(): List<Movie> {
        return moviesAPI.getPopularMovies(BuildConfig.API_KEY).movies
    }

    override suspend fun getGenresFromRemote(): List<Genre> {
        return moviesAPI.getGenre(BuildConfig.API_KEY).genres
    }
}
