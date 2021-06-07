package com.mubassyir.submissionjetpack3.data.remote

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.mubassyir.submissionjetpack3.data.db.model.MovieEntity
import com.mubassyir.submissionjetpack3.data.db.model.TvShow
import com.mubassyir.submissionjetpack3.vo.Resource

interface MovieDataSource {
    fun getAllMovie(): LiveData<Resource<PagedList<MovieEntity>>>

    fun getAllTvShow(): LiveData<Resource<PagedList<TvShow>>>

    fun getDetailMovie(id:Int): LiveData<MovieEntity>

    fun getDetailTvShow(id:Int): LiveData<TvShow>
}