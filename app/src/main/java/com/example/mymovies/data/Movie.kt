package com.example.mymovies.data

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
class Movie() {
    @PrimaryKey var id: Int = 0
    @ColumnInfo var title = ""
    @ColumnInfo var originalTitle = ""
    @ColumnInfo var ratingKinopoisk: Double = 0.0
    @ColumnInfo var releaseDate = 0
    @ColumnInfo var type: String = ""
    @ColumnInfo var posterUrl = ""
    @ColumnInfo var posterLargeUrl = ""
    @ColumnInfo var isFavourite = false
    @ColumnInfo lateinit var genres: List<String>
    constructor(id: Int, title: String, originalTitle: String, rating: Double, realiseDate: Int, type: String, posterUrl: String, posterLargeUrl: String, genres: List<String>, isFavourite: Boolean = false) : this() {
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

    override fun toString(): String {
        return "Movie(id=$id, title='$title', originalTitle='$originalTitle', ratingKinopoisk=$ratingKinopoisk, releaseDate=$releaseDate, type='$type', posterUrl='$posterUrl', posterLargeUrl='$posterLargeUrl', genres=$genres)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Movie

        if (id != other.id) return false
        if (title != other.title) return false
        if (originalTitle != other.originalTitle) return false
        if (ratingKinopoisk != other.ratingKinopoisk) return false
        if (releaseDate != other.releaseDate) return false
        if (type != other.type) return false
        if (posterUrl != other.posterUrl) return false
        if (posterLargeUrl != other.posterLargeUrl) return false
        if (genres != other.genres) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + title.hashCode()
        result = 31 * result + originalTitle.hashCode()
        result = 31 * result + ratingKinopoisk.hashCode()
        result = 31 * result + releaseDate
        result = 31 * result + type.hashCode()
        result = 31 * result + posterUrl.hashCode()
        result = 31 * result + posterLargeUrl.hashCode()
        result = 31 * result + genres.hashCode()
        return result
    }


}