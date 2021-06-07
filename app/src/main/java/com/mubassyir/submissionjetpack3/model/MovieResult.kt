package com.mubassyir.submissionjetpack3.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieResult(

        val overview: String?,

        @SerializedName("release_date")
        val releaseDate: String?,

        val id: Int,

        val title:String?,

        @SerializedName("backdrop_path")
        val backdropPath:String?,

        @SerializedName("original_title")
        val originalTitle: String?,

        @SerializedName("poster_path")
        val posterPath: String?,

        @SerializedName("vote_average")
        val voteAverage: Double?,

        ):Parcelable