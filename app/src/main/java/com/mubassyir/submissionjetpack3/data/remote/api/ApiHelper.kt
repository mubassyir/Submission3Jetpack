package com.mubassyir.submissionjetpack3.data.remote.api

import com.mubassyir.submissionjetpack3.model.MovieResponse
import com.mubassyir.submissionjetpack3.model.MovieResult
import com.mubassyir.submissionjetpack3.model.TvShowResponse
import com.mubassyir.submissionjetpack3.model.TvShowResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiHelper {

    @GET("discover/movie")
    fun getAllMovie(@Query("api_key") apiKey: String?): Call<MovieResponse>

    @GET("discover/tv")
    fun getAllTvShow(@Query("api_key") apiKey: String?) : Call<TvShowResponse>


    @GET("movie/{movie_id}")
    fun getMovieDetils(
        @Path("movie_id")id:Int,
        @Query("api_key")key:String
    ): Call<MovieResult>

    @GET("tv/{tv_id}")
    fun getTvShowDetils(
        @Path("tv_id")id:Int,
        @Query("api_key")key:String
    ): Call<TvShowResult>


}