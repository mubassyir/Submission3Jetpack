package com.mubassyir.submissionjetpack3.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.mubassyir.submissionjetpack3.data.db.model.MovieEntity
import com.mubassyir.submissionjetpack3.repository.MovieRepository

class MovieDetailViewModel(private val movieRepository: MovieRepository): ViewModel() {
    private var id:Int = 0

    fun setId(id:Int){
        this.id = id
    }

    fun getDetailMovie(): LiveData<MovieEntity> {
        return movieRepository.getDetailMovie(this.id)
    }
}