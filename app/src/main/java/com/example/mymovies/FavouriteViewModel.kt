package com.example.mymovies

import android.app.Application
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.mymovies.data.Movie
import java.util.concurrent.Executors

class FavouriteViewModel(application: Application): AndroidViewModel(application) {
    val database = MoviesDatabase.getInstance(application)
    val moviesFav: LiveData<List<Movie>> = database.moviesDao().getAllMovies()
    fun insertMovie(movie: Movie) {
        Handler(Looper.getMainLooper()).post {
            asyncForInsert(movie)
        }
    }

    fun asyncForInsert(vararg movies: Movie) {
        Executors.newSingleThreadExecutor().submit {
            if (movies.isNotEmpty()) database.moviesDao().insertMovie(movies[0])
        }
    }

    fun deleteMovie(movie: Movie) {
        Handler(Looper.getMainLooper()).post {
            asyncForDelete(movie)
        }
    }

    fun asyncForDelete(vararg movie: Movie) {
        Executors.newSingleThreadExecutor().submit {
            if (movie.isNotEmpty()) database.moviesDao().deleteMovie(movie[0].id)
        }

    }
}