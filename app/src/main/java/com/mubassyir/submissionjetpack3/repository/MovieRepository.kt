package com.mubassyir.submissionjetpack3.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.mubassyir.submissionjetpack3.data.db.LocalDataSource
import com.mubassyir.submissionjetpack3.data.db.model.MovieEntity
import com.mubassyir.submissionjetpack3.data.db.model.TvShow
import com.mubassyir.submissionjetpack3.model.MovieResult
import com.mubassyir.submissionjetpack3.model.TvShowResult
import com.mubassyir.submissionjetpack3.data.remote.MovieDataSource
import com.mubassyir.submissionjetpack3.data.remote.RemoteDataSource
import com.mubassyir.submissionjetpack3.utils.AppExecutors
import com.mubassyir.submissionjetpack3.data.remote.api.ApiResponse
import com.mubassyir.submissionjetpack3.data.NetworkBoundResource
import com.mubassyir.submissionjetpack3.vo.Resource


class MovieRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private var localDataSource: LocalDataSource,
    private var appExecutors: AppExecutors
) : MovieDataSource {

    companion object {
        @Volatile
        private var instance: MovieRepository? = null

        fun getInstance(remoteData: RemoteDataSource, localData: LocalDataSource, appExecutors: AppExecutors): MovieRepository =
                instance ?: synchronized(this) {
                    MovieRepository(remoteData, localData, appExecutors).apply { instance = this }
                }
    }

    override fun getAllMovie(): LiveData<Resource<PagedList<MovieEntity>>> {
        return object : NetworkBoundResource<PagedList<MovieEntity>, List<MovieResult>>(appExecutors){
            override fun loadFromDB(): LiveData<PagedList<MovieEntity>> {
                val config = PagedList.Config.Builder().apply {
                    setEnablePlaceholders(false)
                    setInitialLoadSizeHint(4)
                    setPageSize(4)
                }.build()
                return LivePagedListBuilder(localDataSource.getAllMovies(), config).build()
            }

            override fun shouldFetch(data: PagedList<MovieEntity>?): Boolean = data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<MovieResult>>> {
                val movie = MutableLiveData<ApiResponse<List<MovieResult>>>()
                remoteDataSource.getAllMovie(object:RemoteDataSource.LoadMovieCallBack{
                    override fun onAllMoveReceived(movieResult: ApiResponse<List<MovieResult>>){movie.postValue(movieResult)}
                })
                return movie
            }

            override fun saveCallResult(data: List<MovieResult>) {
                val movieList = ArrayList<MovieEntity>()
                for(i in data){
                    val movie = MovieEntity(
                        0,
                        i.title,
                        i.overview,
                        i.releaseDate,
                        i.id,
                        i.backdropPath,
                        i.originalTitle,
                        i.posterPath,
                        i.voteAverage,
                        false
                    )
                    movieList.add(movie)
                }
                localDataSource.cacheMovie(movieList)
            }
        }.asLiveData()
    }


    override fun getAllTvShow(): LiveData<Resource<PagedList<TvShow>>>{
        return object : NetworkBoundResource<PagedList<TvShow>, List<TvShowResult>>(appExecutors){
            override fun loadFromDB(): LiveData<PagedList<TvShow>> {
                val config = PagedList.Config.Builder().apply {
                    setEnablePlaceholders(false)
                    setInitialLoadSizeHint(4)
                    setPageSize(4)
                }.build()
                return LivePagedListBuilder(localDataSource.getAllTvShow(), config).build()
            }
            override fun shouldFetch(data: PagedList<TvShow>?): Boolean = data == null || data.isEmpty()
            override fun createCall(): LiveData<ApiResponse<List<TvShowResult>>> {
                val tvShow = MutableLiveData<ApiResponse<List<TvShowResult>>>()
                remoteDataSource.getAllTvShow(object :RemoteDataSource.LoadTvShowCallBack{
                    override fun onAllTvShowReceived(tvShowRespons: ApiResponse<List<TvShowResult>>) {
                        tvShow.postValue(tvShowRespons)
                    }
                })
                return tvShow
            }

            override fun saveCallResult(data: List<TvShowResult>) {
                val tvShowList = ArrayList<TvShow>()
                for(i in data){
                    val tvShow = TvShow(
                        0,
                        i.name,
                        i.overview,
                        i.firstAirDate,
                        i.id,
                        i.backdropPath,
                        i.originalName,
                        i.posterPath,
                        i.voteAverage,
                        false
                    )
                }
                localDataSource.cacheTvShow(tvShowList)
            }
        }.asLiveData()
    }

    override fun getDetailMovie(id: Int): LiveData<MovieEntity> {
        val detailMovie = MutableLiveData<MovieEntity>()
        detailMovie.postValue(localDataSource.getDetailMovie(id))
        return detailMovie
    }

    override fun getDetailTvShow(id: Int): LiveData<TvShow> {
        val detailTvShow = MutableLiveData<TvShow>()
        detailTvShow.postValue(localDataSource.getDetailTvShow(id))
        return detailTvShow
    }


}