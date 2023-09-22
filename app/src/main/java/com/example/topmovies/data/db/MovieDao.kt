package com.example.topmovies.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.topmovies.data.model.Movie

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMovies(movies: List<Movie>)

    @Query("DELETE FROM movie_table")
    suspend fun deleteAllMovies()

    @Query("SELECT * FROM movie_table")
    fun getAllMovies(): LiveData<List<Movie>>
}
