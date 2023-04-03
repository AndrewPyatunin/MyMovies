package com.example.mymovies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mymovies.data.Movie
import com.squareup.picasso.Picasso

class MoviesDiffCallback(
    private val oldList: List<Movie>,
    private val newList: List<Movie>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldMovie = oldList[oldItemPosition]
        val newMovie = newList[newItemPosition]
        return oldMovie.id == newMovie.id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldMovie = oldList[oldItemPosition]
        val newMovie = newList[newItemPosition]
        return oldMovie == newMovie
    }

}

class MoviesAdapter : RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {

    var onItemClick: ((Movie) -> Unit)? = null
    var onLongMovieClick: ((Movie) -> Unit)? = null
    var movies: List<Movie> = ArrayList()
        set(newValue) {
            val diffCallBack = MoviesDiffCallback(field, newValue)
            val diffResult = DiffUtil.calculateDiff(diffCallBack)
            diffResult.dispatchUpdatesTo(this)
            field = newValue
        }

    inner class MoviesViewHolder(
        itemView: View,
        val imageViewPoster: ImageView = itemView.findViewById(R.id.imageMovie)
    ) : RecyclerView.ViewHolder(itemView) {

        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(movies[adapterPosition])
            }
            itemView.setOnLongClickListener {
                onLongMovieClick?.invoke(movies[adapterPosition])
                true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        return MoviesViewHolder(view)
    }

    override fun onBindViewHolder(moviesViewHolder: MoviesViewHolder, position: Int) {
        val movie = movies[position]
        Picasso.get().load(movie.posterUrl).placeholder(R.drawable.my_image)
            .error(R.drawable.my_image).into(moviesViewHolder.imageViewPoster)

    }

    override fun getItemCount(): Int {
        return movies.size
    }



}