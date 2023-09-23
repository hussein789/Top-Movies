package com.example.topmovies.data.remote

import com.example.topmovies.data.model.GenresList
import com.example.topmovies.data.model.MovieList
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesAPI {

    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("api_key") apiKey: String): MovieList

    @GET("genre/movie/list?language=en")
    suspend fun getGenre(@Query("api_key") apiKey: String): GenresList
}
