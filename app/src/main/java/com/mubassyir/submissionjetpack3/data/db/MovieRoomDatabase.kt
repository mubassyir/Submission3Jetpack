package com.mubassyir.submissionjetpack3.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mubassyir.submissionjetpack3.data.db.model.MovieEntity
import com.mubassyir.submissionjetpack3.data.db.model.TvShow


@Database(entities = [MovieEntity::class, TvShow::class], version = 1)
abstract class MovieRoomDatabase() : RoomDatabase() {

    abstract fun movieDao(): MovieDao

    companion object {
        @Volatile
        private var INSTANCE: MovieRoomDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): MovieRoomDatabase {
            if (INSTANCE == null) {
                synchronized(MovieRoomDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        MovieRoomDatabase::class.java, "movie_database"
                    )
                        .build()
                }
            }
            return INSTANCE as MovieRoomDatabase
        }
    }
}