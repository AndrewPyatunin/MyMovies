package com.example.mymovies.utils

import com.example.mymovies.data.Movie
import org.json.JSONObject

class JSONUtils {
    companion object {
        val KEY_ITEMS = "items"
        val KEY_KINO_ID = "kinopoiskId"
        val KEY_TITLE = "nameRu"
        val KEY_TITLE_ORIGINAL = "nameOriginal"
        val KEY_RATING = "ratingKinopoisk"
        val KEY_YEAR = "year"
        val KEY_TYPE = "type"
        val KEY_POSTER_URL_PREVIEW = "posterUrlPreview"
        val KEY_POSTER_URL = "posterUrl"
        val KEY_GENRES = "genres"

        fun getMoviesFromJSON(jsonObject: JSONObject?): ArrayList<Movie> {
            val list = ArrayList<Movie>()
            if(jsonObject == null) return list
            val jsonArray = jsonObject.getJSONArray(KEY_ITEMS)
            for(i in 0 until jsonArray.length()) {
                val objectMovie = jsonArray.getJSONObject(i)
                val id = objectMovie.getInt(KEY_KINO_ID)
                val title = objectMovie.getString(KEY_TITLE)
                val originalTitle = objectMovie.getString(KEY_TITLE_ORIGINAL)
                val rating = objectMovie.getDouble(KEY_RATING)
                val type = objectMovie.getString(KEY_TYPE)
                val image = objectMovie.getString(KEY_POSTER_URL_PREVIEW)
                val largeImage = objectMovie.getString(KEY_POSTER_URL)
                val year = objectMovie.getInt(KEY_YEAR)
                val genresArray = objectMovie.getJSONArray(KEY_GENRES)
                val genres = ArrayList<String>()
                for(j in 0 until genresArray.length()) {
                    genres.add(genresArray.getJSONObject(j).getString("genre"))
                }

                list.add(Movie(id, title, originalTitle, rating, year, type, image, largeImage, genres))
            }
            return list
        }
    }
}