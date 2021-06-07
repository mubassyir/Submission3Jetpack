package com.mubassyir.submissionjetpack3.di

import android.content.Context
import com.mubassyir.submissionjetpack3.data.db.LocalDataSource
import com.mubassyir.submissionjetpack3.data.db.MovieRoomDatabase
import com.mubassyir.submissionjetpack3.data.remote.RemoteDataSource
import com.mubassyir.submissionjetpack3.data.remote.api.ApiConfig
import com.mubassyir.submissionjetpack3.repository.MovieRepository
import com.mubassyir.submissionjetpack3.utils.AppExecutors

object Injection {
    fun provideRepository(context: Context): MovieRepository {

        val database = MovieRoomDatabase.getDatabase(context)

        val remoteDataSource = RemoteDataSource.getInstance(ApiConfig())
        val localDataSource = LocalDataSource.getInstance(database.movieDao())
        val appExecutors = AppExecutors()

        return MovieRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}