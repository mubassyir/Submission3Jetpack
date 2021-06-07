package com.mubassyir.submissionjetpack3.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mubassyir.submissionjetpack3.di.Injection
import com.mubassyir.submissionjetpack3.repository.MovieRepository

class ViewModelFactory private constructor(private val movieRepository: MovieRepository):
    ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                ViewModelFactory(Injection.provideRepository(context)).apply { instance = this }
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MovieViewModel::class.java) -> {
                MovieViewModel(movieRepository) as T
            }
            modelClass.isAssignableFrom(TvShowViewModel::class.java) -> {
                TvShowViewModel(movieRepository) as T
            }
            modelClass.isAssignableFrom(MovieDetailViewModel::class.java) -> {
                MovieDetailViewModel(movieRepository) as T
            }
            modelClass.isAssignableFrom(TvShowDetailViewModel::class.java) -> {
                TvShowDetailViewModel(movieRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
    }
}