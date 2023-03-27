package com.example.mymovies

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.mymovies.data.Movie

@Dao
interface MoviesDao {
    @Query("SELECT * FROM movies")
    fun getAllMovies(): List<Movie>

    @Insert
    fun insertMovie(movie: Movie)

    @Delete
    fun deleteMovie(movie: Movie)
}