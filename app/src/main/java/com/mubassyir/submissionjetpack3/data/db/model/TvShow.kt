package com.mubassyir.submissionjetpack3.data.db.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName= "tvShow")
data class TvShow(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,

    @ColumnInfo(name = "name")
    var name: String? = null,

    @ColumnInfo(name="overview")
    var overview: String?=null,

    @ColumnInfo(name = "first_air_date")
    var firstAirDate: String?=null,

    @ColumnInfo(name = "tv_id")
    var tvId: Int=0,

    @ColumnInfo(name = "backdrop_path")
    var backdropPath:String?=null,

    @ColumnInfo(name = "original_name")
    var originalName: String?=null,

    @ColumnInfo(name = "poster_path")
    var posterPath: String?=null,

    @ColumnInfo(name = "vote_average")
    var voteAverage: Double?=0.0,

    @ColumnInfo(name="is_favorite")
    var isFavorite:Boolean=false
): Parcelable
