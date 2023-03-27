package com.example.mymovies

import androidx.room.TypeConverter

class ConvertersListToString {
    @TypeConverter
    fun fromList(list: List<String>?): String {
        return list.toString()
    }

    @TypeConverter
    fun fromString(string: String): List<String> {
        return string.split(" ")
    }
}