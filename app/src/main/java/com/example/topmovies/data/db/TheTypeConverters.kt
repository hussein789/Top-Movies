package com.example.topmovies.data.db

import androidx.room.TypeConverter

class TheTypeConverters {
    @TypeConverter
    fun fromListIntToString(intList: List<Int>): String = intList.toString()

    @TypeConverter
    fun toListIntFromString(stringList: String): List<Int> {
        val result = ArrayList<Int>()
        val split = stringList.replace("[", "").replace("]", "").replace(" ", "").split(",")
        for (n in split) {
            try {
                result.add(n.toInt())
            } catch (e: Exception) {
            }
        }
        return result
    }
}
