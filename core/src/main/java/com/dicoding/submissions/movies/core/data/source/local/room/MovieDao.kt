package com.dicoding.submissions.movies.core.data.source.local.room

import androidx.paging.DataSource
import androidx.room.*
import com.dicoding.submissions.movies.core.data.source.local.entity.MovieEntity
import com.dicoding.submissions.movies.core.data.source.local.entity.TvEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Query("SELECT * FROM movieentities")
    fun getMovies(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM tventities")
    fun getTv(): Flow<List<TvEntity>>

    @Query("SELECT * FROM movieentities where favorite = 1")
    fun getFavoriteMovie(): DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM tventities where favorite = 1")
    fun getFavoriteTv(): DataSource.Factory<Int, TvEntity>

    @Transaction
    @Query("SELECT * FROM movieentities WHERE movieId = :movieId")
    fun getDetailMovie(movieId: String): Flow<MovieEntity>

    @Transaction
    @Query("SELECT * FROM tventities WHERE tvId = :tvId")
    fun getDetailTv(tvId: String): Flow<TvEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTv(tv : List<TvEntity>)

    @Update
    suspend fun insertDetailMovie(movie: MovieEntity)

    @Update
    suspend fun insertDetailTv(tv: TvEntity)

    @Update
    fun updateMovie(movie: MovieEntity)

    @Update
    fun updateTv(tvId: TvEntity)
}