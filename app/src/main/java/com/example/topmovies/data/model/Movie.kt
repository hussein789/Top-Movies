package com.example.topmovies.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Movie_table")
data class Movie(
    @PrimaryKey
    @SerializedName("id")
    val id: Int,
    @SerializedName("overview")
    val overview: String?,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("release_date")
    val releaseDate: String?,
    @SerializedName("title")
    val title: String,
    @SerializedName("vote_average")
    val rating: Double,
    @SerializedName("genre_ids")
    val genreIds: List<Int>,
)

fun Movie.getImageUrl() = "https://image.tmdb.org/t/p/w500$posterPath"
