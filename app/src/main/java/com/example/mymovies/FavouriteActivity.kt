package com.example.mymovies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mymovies.databinding.ActivityFavouriteBinding

class FavouriteActivity : AppCompatActivity() {
    lateinit var binding: ActivityFavouriteBinding
    private lateinit var adapter: MoviesAdapter
    lateinit var progress: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavouriteBinding.inflate(layoutInflater).also { setContentView(it.root) }
        with(binding) {
            recyclerViewFavourites.layoutManager = GridLayoutManager(applicationContext, 2)
            recyclerViewFavourites.isNestedScrollingEnabled = true
        }
//        MainActivity.ISDOWNLOADED++
        adapter = MoviesAdapter()
        adapter.movies = MainActivity.favouriteMovies
        binding.recyclerViewFavourites.adapter = adapter
    }

}