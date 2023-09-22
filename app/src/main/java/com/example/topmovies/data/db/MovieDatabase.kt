package com.example.topmovies.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.topmovies.data.model.Movie

@Database(entities = [Movie::class], version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}
