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
): DiffUtil.Callback() {
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
//    val executor = Executors.newSingleThreadExecutor()
//    var loaderProgress: ProgressBar? = null
    var movies: ArrayList<Movie> = ArrayList()
    set(newValue) {
        val diffCallBack = MoviesDiffCallback(field, newValue)
        val diffResult = DiffUtil.calculateDiff(diffCallBack)
        field = newValue
        diffResult.dispatchUpdatesTo(this)
    }

    inner class MoviesViewHolder(itemView: View, val imageViewPoster: ImageView = itemView.findViewById(R.id.imageMovie)):RecyclerView.ViewHolder(itemView) {

        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(movies[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        return MoviesViewHolder(view)
    }

    override fun onBindViewHolder(moviesViewHolder: MoviesViewHolder, position: Int) {
        val movie = movies[position]
//        if (MainActivity.ISDOWNLOADED==0) {
        Picasso.get().load(movie.posterUrl).placeholder(R.drawable.my_image)
            .error(R.drawable.my_image).into(moviesViewHolder.imageViewPoster)

//        } else {
//            Picasso.get().load(movie.posterUrl).placeholder(R.drawable.my_image)
//                .error(R.drawable.my_image).into(moviesViewHolder.imageViewPoster)
//        }
        //moviesViewHolder.imageViewPoster.setImageBitmap(getImage(movie.posterUrl))
    }

    override fun getItemCount(): Int {
        return movies.size
    }

//    fun getImage(vararg urls: String): Bitmap {
//        val future = executor.submit(object: Callable<Bitmap>{
//            override fun call(): Bitmap {
//                var bitmap: Bitmap ?= null
//                var connection: HttpURLConnection ?= null
//                try {
//                    var url = URL(urls[0])
//                    connection = url.openConnection() as HttpURLConnection
//                    var inputStream = connection.inputStream
//                    bitmap = BitmapFactory.decodeStream(inputStream)
//
//                } catch (e: Exception) {
//                    e.printStackTrace()
//                } finally {
//                    connection?.disconnect()
//                }
//                if(bitmap!=null) {
//                    return bitmap
//                } else throw Exception("Not found image!")
//            }
//
//        })
//        return future.get()
//    }


}