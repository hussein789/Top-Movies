package com.example.topmovies.data.remote

import com.example.topmovies.data.model.MovieList
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesAPI {

    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("api_key") apiKey: String): MovieList
}
