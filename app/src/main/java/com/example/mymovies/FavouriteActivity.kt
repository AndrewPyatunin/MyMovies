package com.example.mymovies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.mymovies.MainActivity.Companion.favouriteMovies
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
        adapter.movies = favouriteMovies
        binding.recyclerViewFavourites.adapter = adapter
        adapter.onLongMovieClick = {
            it.isFavourite = false
            adapter.movies.remove(it)
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
                    adapter.movies.removeAt(position)
                    adapter.notifyItemRemoved(position)
                }

            })
        itemTouch.attachToRecyclerView(binding.recyclerViewFavourites)
    }

}