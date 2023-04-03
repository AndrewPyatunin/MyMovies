package com.example.mymovies

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mymovies.data.Movie

@Dao
interface MoviesDao {
    @Query("SELECT * FROM movies")
    fun getAllMovies(): LiveData<List<Movie>>

    @Query("SELECT * FROM movies WHERE id = :id")
    fun getById(id: Int): Movie

    @Update
    fun update(movie: Movie)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: Movie)

    @Query("DELETE FROM movies WHERE id = :id")
    fun deleteMovie(id: Int)
}