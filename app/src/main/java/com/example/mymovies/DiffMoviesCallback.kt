package com.example.mymovies

import androidx.recyclerview.widget.DiffUtil
import com.example.mymovies.data.Movie

class DiffMoviesCallback: DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }
}