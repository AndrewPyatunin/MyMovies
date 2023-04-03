package com.example.mymovies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.mymovies.data.Movie
import com.example.mymovies.databinding.ActivityFavouriteBinding

class FavouriteActivity : AppCompatActivity() {
    lateinit var binding: ActivityFavouriteBinding
    private lateinit var adapter: MoviesAdapter
    lateinit var viewModel: FavouriteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavouriteBinding.inflate(layoutInflater).also { setContentView(it.root) }
        viewModel = ViewModelProvider(this)[FavouriteViewModel::class.java]
        with(binding) {
            recyclerViewFavourites.layoutManager = GridLayoutManager(applicationContext, 2)
            recyclerViewFavourites.isNestedScrollingEnabled = true
        }
        adapter = MoviesAdapter()
        getData()
        binding.recyclerViewFavourites.adapter = adapter
        adapter.onLongMovieClick = {
            it.isFavourite = false
            (adapter.movies as ArrayList<Movie>).remove(it)
        }

        val itemTouch =
            ItemTouchHelper(object :
                ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    TODO("Not yet implemented")
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    val position = viewHolder.adapterPosition
                    adapter.movies[position].isFavourite = false
                    remove(position)
                }

            })
        itemTouch.attachToRecyclerView(binding.recyclerViewFavourites)
    }

    fun remove(position: Int) {
        val movie = adapter.movies[position]
        viewModel.deleteMovie(movie)
    }

    fun getData() {
        val moviesFromDb = viewModel.moviesFav
        moviesFromDb.observe(this, object: Observer<List<Movie>> {
            override fun onChanged(value: List<Movie>) {
                adapter.movies = value
            }

        })
    }
}