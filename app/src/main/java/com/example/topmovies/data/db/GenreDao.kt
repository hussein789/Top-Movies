package com.example.topmovies.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.topmovies.data.model.Genre

@Dao
interface GenreDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGenres(genres: List<Genre>)

    @Query("DELETE FROM genre_table")
    suspend fun deleteAll()

    @Query("SELECT * FROM genre_table")
    suspend fun getAllGenre(): List<Genre>
}
