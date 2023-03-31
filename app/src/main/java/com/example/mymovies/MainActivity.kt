package com.example.mymovies

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mymovies.data.Movie
import com.example.mymovies.databinding.ActivityMainBinding
import com.example.mymovies.utils.NetworkUtils

class MainActivity : AppCompatActivity() {
    var loaderProgress: ProgressBar? = null
    lateinit var menu: Menu
    var imageViewPoster: ImageView? = null
    companion object {
        @JvmStatic var ISDOWNLOADED = 0
        val favouriteMovies = ArrayList<Movie>()
        var movies = ArrayList<Movie>()
    }
    private lateinit var adapter: MoviesAdapter
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loaderProgress = binding.progressBar
        loaderProgress?.visibility = View.VISIBLE
//        imageViewPoster = findViewById(R.id.imageMovie)
        //val url = NetworkUtils.buildURL(true, 2)

        with(binding) {
            recyclerViewMovies.layoutManager = GridLayoutManager(applicationContext, 2)
            //recyclerViewMovies?.setHasFixedSize(true)
            //adapter?.setHasStableIds(true)
            recyclerViewMovies.isNestedScrollingEnabled = false
        }


        adapter = MoviesAdapter()
        binding.recyclerViewMovies.adapter = adapter

        NetworkUtils.getJSONFromNetwork(true, 1) {

            Log.i("LOG", Thread.currentThread().name)
            adapter.movies = it
            loaderProgress?.visibility = View.GONE
            //adapter?.notifyDataSetChanged()
        }
        movies = adapter.movies
        adapter.onItemClick = {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("position", movies.indexOf(it))
            startActivity(intent)
        }
//        Log.i("LIST", list.map { it.toString() }.toString())
        //Log.i("JSONObject", (jsonObject)?.getJSONArray("items")?.getJSONObject(0)?.getString("nameRu").toString())
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_movies, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId==R.id.action_favourites) {
            val intent = Intent(this, FavouriteActivity::class.java)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }


//    fun openFilmDetails() {
//        val intent = Intent(this, DetailActivity::class.java)
//        startActivity(intent)
//    }
}