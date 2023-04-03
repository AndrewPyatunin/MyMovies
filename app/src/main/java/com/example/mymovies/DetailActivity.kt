package com.example.mymovies

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider
import com.example.mymovies.MainActivity.Companion.beginMovies
import com.example.mymovies.data.Movie
import com.example.mymovies.databinding.ActivityDetailBinding
import com.example.mymovies.utils.NetworkUtils.Companion.FILM
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class DetailActivity : AppCompatActivity(), Callback {

    lateinit var loaderView: View
    lateinit var binding: ActivityDetailBinding
    lateinit var viewModel: FavouriteViewModel
    lateinit var database: MoviesDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater).also { setContentView(it.root) }
        viewModel = ViewModelProvider(this)[FavouriteViewModel::class.java]
        database = MoviesDatabase.getInstance(this)
        loaderView = findViewById(R.id.progress)
        if (binding.textViewTitle.text == "") binding.imageViewFavourites.setImageResource(R.drawable.starempty)

        val movieFromMain = intent.extras?.getSerializable("movie") as Movie
        val movie = beginMovies[beginMovies.indexOf(movieFromMain)]
        val type =
            if (movie.type == FILM) getString(R.string.movie) else getString(R.string.tv_series)
        if (movie.isFavourite) binding.imageViewFavourites.setImageResource(R.drawable.favourite)

        with(binding) {
            textViewTitle.text = String.format(
                getString(R.string.textViewTitle),
                type,
                movie.title,
                movie.releaseDate
            )
            textViewRating.text =
                String.format(getString(R.string.textViewRating), movie.ratingKinopoisk)
            textViewGenres.text =
                String.format(getString(R.string.textViewGenres), movie.genres.joinToString())
            imageViewFavourites.setOnClickListener {
                if (!movie.isFavourite) {
                    (it as ImageView).setImageResource(R.drawable.favourite)
                    movie.isFavourite = true
                    viewModel.insertMovie(movie)
                } else {
                    (it as ImageView).setImageResource(R.drawable.starempty)
                    movie.isFavourite = false
                    viewModel.deleteMovie(movie)
                }
            }
        }

        Picasso.get().load(movie.posterLargeUrl).placeholder(R.drawable.image_detail)
            .error(R.drawable.image_detail).into(binding.imageViewPoster, this)
    }


    override fun onSuccess() {
        loaderView.visibility = View.GONE
        binding.imageViewPoster.visibility = View.VISIBLE
    }

    override fun onError(e: java.lang.Exception?) {
        loaderView.visibility = View.GONE
        binding.imageViewPoster.visibility = View.VISIBLE
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