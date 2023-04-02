package com.example.mymovies.utils

import android.net.Uri
import android.os.AsyncTask
import android.os.Handler
import android.os.Looper
import android.util.Log
import com.example.mymovies.data.Movie
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.util.concurrent.Callable
import java.util.concurrent.Executors

class NetworkUtils {


    companion object {
        val executor = Executors.newSingleThreadExecutor()
        lateinit var result: URL
        val API_KEY = "69a856f4-a749-48b9-b3fb-960f89900676"
        val BASE_URL = "https://kinopoiskapiunofficial.tech/api/v2.2/films"
        val LANGUAGE_VALUE = "ru"
        val SORT = "order"
        val SORT_BY_RATING = "RATING"
        val SORT_BY_POPULARITY = "NUM_VOTE"
        val TYPE = "type"
        val COUNT = "count"
        val RATING = "ratingFrom"
        val PAGE = "page"
        val FILM = "FILM"
        val TV = "TV_SERIES"

        @Volatile
        var type = ""
        fun buildURL(typeFilm: Boolean, page: Int): URL {
            type = if (typeFilm) FILM else TV
            val uri: Uri = Uri.parse(BASE_URL).buildUpon()
                .appendQueryParameter(SORT, SORT_BY_POPULARITY)
                .appendQueryParameter(TYPE, type)
                .appendQueryParameter(RATING, "7")
                .appendQueryParameter(PAGE, "$page")
                .build()
            try {
                result = URL(uri.toString())
//                res = result.openConnection() as HttpURLConnection
//                res.setRequestProperty("accept", "application/json")
//                res.setRequestProperty("X-API-KEY", "")
            } catch (e: MalformedURLException) {
                e.printStackTrace()
            }
            return result
        }

        fun getJSONFromNetwork(
            typeFilm: Boolean,
            page: Int,
            onSuccess: (ArrayList<Movie>) -> Unit
        ) {
            try {
                doInBackground(buildURL(typeFilm, page), onSuccess = onSuccess)
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }

        fun doInBackground(vararg urls: URL, onSuccess: (ArrayList<Movie>) -> Unit) {

            executor.submit {
                var result1: JSONObject? = null
                var connection: HttpURLConnection? = null
                try {
                    Log.i("RESULT_", Thread.currentThread().name)
                    connection = urls[0].openConnection() as HttpURLConnection
                    connection.setRequestProperty("X-API-KEY", API_KEY)
                    connection.setRequestProperty("accept", "application/json")
                    var inputStream = connection.inputStream
                    val reader = BufferedReader(InputStreamReader(inputStream))
                    var builder = StringBuilder()
                    var line = reader.readLine()
                    while (line != null) {
                        builder.append(line)
                        line = reader.readLine()
                    }
                    val result = JSONUtils.getMoviesFromJSON(JSONObject(builder.toString()))
                    onSuccess(result)
                    //doSomethingOnUi(result1)

                } catch (e: Exception) {
                    e.printStackTrace()
                } finally {
                    connection?.disconnect()
                }
            }
            //if (urls == null || urls.isEmpty()) result1


            //return result as JSONObject
        }

//        fun doSomethingOnUi(jsonObject: JSONObject?) {
//            Handler(Looper.getMainLooper()).post {
//                executor.shutdown()
//                //Log.i("JSON", (jsonObject)?.getJSONArray("results")?.getJSONObject(0)?.getString("title").toString())
//            }
//        }
    }


}