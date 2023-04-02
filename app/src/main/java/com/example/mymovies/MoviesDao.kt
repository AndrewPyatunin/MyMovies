package com.example.mymovies

import androidx.room.*
import com.example.mymovies.data.Movie

@Dao
interface MoviesDao {
    @Query("SELECT * FROM movies")
    fun getAllMovies(): List<Movie>

    @Query("SELECT * FROM movies WHERE id = :id")
    fun getById(id: Int): Movie

    @Update
    fun update(movie: Movie)

    @Insert
    fun insertMovie(movie: Movie)

    @Delete
    fun deleteMovie(movie: Movie)
}