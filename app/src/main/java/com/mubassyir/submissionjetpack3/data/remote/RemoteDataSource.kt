package com.mubassyir.submissionjetpack3.data.remote

import android.os.Handler
import android.os.Looper
import com.mubassyir.submissionjetpack3.BuildConfig
import com.mubassyir.submissionjetpack3.data.remote.api.ApiConfig
import com.mubassyir.submissionjetpack3.model.MovieResponse
import com.mubassyir.submissionjetpack3.model.MovieResult
import com.mubassyir.submissionjetpack3.model.TvShowResponse
import com.mubassyir.submissionjetpack3.model.TvShowResult
import com.mubassyir.submissionjetpack3.utils.EspressoIdlingResource
import com.mubassyir.submissionjetpack3.data.remote.api.ApiResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource private constructor(private val apiConfig: ApiConfig) {

    private val handler = Handler(Looper.getMainLooper())

    companion object {
        private const val API_KEY = BuildConfig.API_KEY
        private const val SERVICE_LATENCY_IN_MILLIS: Long = 2000

        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(helper: ApiConfig): RemoteDataSource =
            instance ?: synchronized(this) {
                RemoteDataSource(helper).apply { instance = this }
            }
    }

    fun getAllMovie(callback: LoadMovieCallBack) {
        EspressoIdlingResource.increment()
        handler.postDelayed({
            apiConfig.getApiService().getAllMovie(API_KEY).enqueue(object :
                Callback<MovieResponse> {
                override fun onResponse(
                    call: Call<MovieResponse>,
                    response: Response<MovieResponse>
                ) {
                    callback.onAllMoveReceived(ApiResponse.success(response.body()?.results!!))
                    EspressoIdlingResource.decrement()
                }

                override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                    ApiResponse.error("getAllMovie",t.message)
                }
            })
        }, SERVICE_LATENCY_IN_MILLIS)
    }

    fun getAllTvShow(callBack: LoadTvShowCallBack) {
        EspressoIdlingResource.increment()
        handler.postDelayed({
            apiConfig.getApiService().getAllTvShow(API_KEY).enqueue(object :
                Callback<TvShowResponse> {
                override fun onResponse(
                    call: Call<TvShowResponse>,
                    response: Response<TvShowResponse>
                ) {
                    callBack.onAllTvShowReceived(ApiResponse.success(response.body()?.results!!))
                    EspressoIdlingResource.decrement()
                }
                override fun onFailure(call: Call<TvShowResponse>, t: Throwable) {
                    ApiResponse.error("getAllTvShow",t.message)
                }
            })
        }, SERVICE_LATENCY_IN_MILLIS)
    }


    interface LoadMovieCallBack {
        fun onAllMoveReceived(movieResult: ApiResponse<List<MovieResult>>)
    }

    interface LoadTvShowCallBack {
        fun onAllTvShowReceived(tvShowRespons: ApiResponse<List<TvShowResult>>)
    }

}