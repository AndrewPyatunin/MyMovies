package com.example.mymovies

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.mymovies.data.Movie

@Database(version = 1, entities = [Movie::class], exportSchema = false)
@TypeConverters(ConvertersListToString::class)
abstract class MoviesDatabase: RoomDatabase() {

    companion object {
        var database: MoviesDatabase? = null
        private const val DB_NAME = "moviesFavourite.db"
        private val LOCK = Any()
        fun getInstance(context: Context): MoviesDatabase {
            synchronized(LOCK) {
                if (database == null) database = Room.databaseBuilder(context.applicationContext, MoviesDatabase::class.java, DB_NAME).build()
                return database as MoviesDatabase
            }
        }
    }
    abstract fun moviesDao(): MoviesDao

}