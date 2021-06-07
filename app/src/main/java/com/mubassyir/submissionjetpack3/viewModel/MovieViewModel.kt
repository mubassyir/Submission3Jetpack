package com.mubassyir.submissionjetpack3.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.mubassyir.submissionjetpack3.data.db.model.MovieEntity
import com.mubassyir.submissionjetpack3.repository.MovieRepository
import com.mubassyir.submissionjetpack3.vo.Resource

class MovieViewModel(private val movieRepository: MovieRepository):ViewModel() {

    fun getAllMovie(): LiveData<Resource<PagedList<MovieEntity>>> {
        return movieRepository.getAllMovie()
    }
}