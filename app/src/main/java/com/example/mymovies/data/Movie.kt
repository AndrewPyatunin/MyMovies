package com.example.mymovies.data

import androidx.room.*

@Entity(tableName = "movies", indices = [Index("title", unique = true)])
data class Movie(@PrimaryKey var id: Int = 0) : java.io.Serializable {
    @ColumnInfo var title = ""
    @ColumnInfo(name = "original_title") var originalTitle = ""
    @ColumnInfo(name = "rating_kinopoisk") var ratingKinopoisk: Double = 0.0
    @ColumnInfo(name = "release_date") var releaseDate = 0
    @ColumnInfo var type: String = ""
    @ColumnInfo(name = "poster_url") var posterUrl = ""
    @ColumnInfo(name = "poster_large_url") var posterLargeUrl = ""
    @ColumnInfo(name = "is_favourite") var isFavourite = false
    @ColumnInfo lateinit var genres: List<String>

    constructor(
        id: Int,
        title: String,
        originalTitle: String,
        rating: Double,
        realiseDate: Int,
        type: String,
        posterUrl: String,
        posterLargeUrl: String,
        genres: List<String>,
        isFavourite: Boolean = false
    ) : this(id) {
        this.id = id
        this.title = title
        this.originalTitle = originalTitle
        this.ratingKinopoisk = rating
        this.releaseDate = realiseDate
        this.type = type
        this.posterUrl = posterUrl
        this.posterLargeUrl = posterLargeUrl
        this.genres = genres
        this.isFavourite = isFavourite
    }


}