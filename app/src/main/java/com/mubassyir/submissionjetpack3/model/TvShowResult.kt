package com.mubassyir.submissionjetpack3.model

import com.google.gson.annotations.SerializedName

data class TvShowResult(

        @SerializedName("first_air_date")
        var firstAirDate: String?,

        var id: Int,

        @SerializedName("backdrop_path")
        var backdropPath: String?,

        var name: String?,

        @SerializedName("original_name")
        var originalName: String?,

        var overview: String?,

        @SerializedName("poster_path")
        var posterPath: String?,

        @SerializedName("vote_average")
        var voteAverage: Double?,

        )