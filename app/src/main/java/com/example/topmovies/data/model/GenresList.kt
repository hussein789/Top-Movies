package com.example.topmovies.data.model


import com.google.gson.annotations.SerializedName

data class GenresList(
    @SerializedName("genres")
    val genres: List<Genre>
)