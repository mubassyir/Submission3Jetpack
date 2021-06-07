package com.mubassyir.submissionjetpack3.data.db.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="movie")
data class MovieEntity(

        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id")
        var id: Int = 0,

        @ColumnInfo(name = "title")
        var title: String?=null,

        @ColumnInfo(name="overview")
        var overview: String?=null,

        @ColumnInfo(name = "release_date")
        var releaseDate: String?=null,

        @NonNull
        @ColumnInfo(name = "movie_id")
        var movieId: Int=0,

        @ColumnInfo(name = "backdrop_path")
        var backdropPath:String?=null,

        @ColumnInfo(name = "original_title")
        var originalTitle: String?=null,

        @ColumnInfo(name = "poster_path")
        var posterPath: String?=null,

        @ColumnInfo(name = "vote_average")
        var voteAverage: Double?=0.0,

        @ColumnInfo(name="is_favorite")
        var isFavorite:Boolean=false
)