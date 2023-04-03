package com.example.mymovies

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mymovies.data.Movie
import com.example.mymovies.databinding.ActivityMainBinding
import com.example.mymovies.utils.NetworkUtils

class MainActivity : AppCompatActivity() {
    var loaderProgress: ProgressBar? = null

    companion object {
        var beginMovies = ArrayList<Movie>()
    }

    private lateinit var adapter: MoviesAdapter
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loaderProgress = binding.progressBar
        loaderProgress?.visibility = View.VISIBLE

        with(binding) {
            recyclerViewMovies.layoutManager = GridLayoutManager(applicationContext, 2)
            recyclerViewMovies.isNestedScrollingEnabled = false
        }


        adapter = MoviesAdapter()
        binding.recyclerViewMovies.adapter = adapter

        NetworkUtils.getJSONFromNetwork(true, 1) {

            Handler(Looper.getMainLooper()).post {
                beginMovies = it
                adapter.movies = beginMovies
                loaderProgress?.visibility = View.GONE
            }
        }
        adapter.onItemClick = {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("movie", it)
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_movies, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_favourites) {
            val intent = Intent(this, FavouriteActivity::class.java)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }

}