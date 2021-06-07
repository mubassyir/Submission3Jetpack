package com.mubassyir.submissionjetpack3.data.db

import androidx.paging.DataSource
import androidx.room.*
import com.mubassyir.submissionjetpack3.data.db.model.MovieEntity
import com.mubassyir.submissionjetpack3.data.db.model.TvShow

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun cacheMovie(movies: List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun cacheTvShow(tvShow: List<TvShow>)

    @Query("SELECT * FROM movie ORDER BY id ASC")
    fun getAllMovie(): DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM tvshow ORDER BY id ASC")
    fun getAlltvShow(): DataSource.Factory<Int, TvShow>

    @Query("SELECT * FROM movie WHERE movie_id= :movieId")
    fun getDetailMovie(movieId:Int):MovieEntity

    @Query("SELECT * FROM tvShow WHERE tv_id= :tvId")
    fun getDetailTvShow(tvId:Int):TvShow

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertMovie (movie: MovieEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertTv (tvShow: TvShow)

    @Delete
    fun deleteMovie(movie: MovieEntity)

    @Delete
    fun deleteTv(tvShow: TvShow)



}