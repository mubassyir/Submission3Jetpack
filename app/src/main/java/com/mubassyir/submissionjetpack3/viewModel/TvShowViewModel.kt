package com.mubassyir.submissionjetpack3.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.mubassyir.submissionjetpack3.data.db.model.TvShow
import com.mubassyir.submissionjetpack3.repository.MovieRepository
import com.mubassyir.submissionjetpack3.vo.Resource

class TvShowViewModel(private val movieRepository: MovieRepository):ViewModel() {
    fun getAllTvShow(): LiveData<Resource<PagedList<TvShow>>> {
        return movieRepository.getAllTvShow()
    }
}