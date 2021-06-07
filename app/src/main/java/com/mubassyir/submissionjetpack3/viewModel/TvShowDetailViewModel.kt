package com.mubassyir.submissionjetpack3.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.mubassyir.submissionjetpack3.data.db.model.TvShow
import com.mubassyir.submissionjetpack3.repository.MovieRepository

class TvShowDetailViewModel(private var movieRepository: MovieRepository):ViewModel() {
    private var id:Int = 0

    fun setId(id:Int){
        this.id = id
    }

    fun getDetailTvShow(): LiveData<TvShow> {
        return movieRepository.getDetailTvShow(this.id)
    }
}