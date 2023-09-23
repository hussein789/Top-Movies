package com.example.topmovies.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.topmovies.data.model.Genre
import com.example.topmovies.data.model.Movie

@Database(entities = [Movie::class, Genre::class], version = 3, exportSchema = false)
@TypeConverters(TheTypeConverters::class)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun genreDao(): GenreDao
}
