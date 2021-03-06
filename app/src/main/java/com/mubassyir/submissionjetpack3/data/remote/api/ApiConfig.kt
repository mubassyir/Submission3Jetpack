package com.mubassyir.submissionjetpack3.data.remote.api

import com.mubassyir.submissionjetpack3.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiConfig{

    companion object{
        private const val BASE_URL = BuildConfig.BASE_URL
    }
    fun getApiService(): ApiHelper {
        val client = OkHttpClient.Builder()
            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        return retrofit.create(ApiHelper::class.java)
    }
}