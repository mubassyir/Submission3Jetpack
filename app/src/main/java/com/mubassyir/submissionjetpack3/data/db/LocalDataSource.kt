package com.mubassyir.submissionjetpack3.data.db

import androidx.paging.DataSource
import com.mubassyir.submissionjetpack3.data.db.model.MovieEntity
import com.mubassyir.submissionjetpack3.data.db.model.TvShow


class LocalDataSource (private val movieDao: MovieDao){

    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(academyDao: MovieDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(academyDao).apply {
                INSTANCE = this
            }
    }

    fun cacheMovie(movie:List<MovieEntity>){
        movieDao.cacheMovie(movie)
    }

    fun cacheTvShow(tvShow:List<TvShow>){
        movieDao.cacheTvShow(tvShow)
    }

    fun getDetailMovie(id:Int): MovieEntity {
        return movieDao.getDetailMovie(id)
    }

    fun getDetailTvShow(id:Int): TvShow {
        return movieDao.getDetailTvShow(id)
    }

    fun getAllMovies(): DataSource.Factory<Int, MovieEntity> = movieDao.getAllMovie()


    fun getAllTvShow():DataSource.Factory<Int, TvShow> = movieDao.getAlltvShow()
}