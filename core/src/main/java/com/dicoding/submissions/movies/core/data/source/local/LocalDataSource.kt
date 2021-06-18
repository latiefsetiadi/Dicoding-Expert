package com.dicoding.submissions.movies.core.data.source.local

import androidx.paging.DataSource
import com.dicoding.submissions.movies.core.data.source.local.entity.MovieEntity
import com.dicoding.submissions.movies.core.data.source.local.entity.TvEntity
import com.dicoding.submissions.movies.core.data.source.local.room.MovieDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val mMovieDao: MovieDao){


    fun getMovies(): Flow<List<MovieEntity>> = mMovieDao.getMovies()

    fun getTv(): Flow<List<TvEntity>> = mMovieDao.getTv()

    fun getDetailMovies(movieId: String): Flow<MovieEntity> = mMovieDao.getDetailMovie(movieId)

    fun getDetailTv(tvId: String): Flow<TvEntity> = mMovieDao.getDetailTv(tvId)

    suspend fun insertMovie(movies: List<MovieEntity>) = mMovieDao.insertMovies(movies)

    suspend fun insertTv(tv: List<TvEntity>) = mMovieDao.insertTv(tv)

    suspend fun insertDetailMovie(movie: MovieEntity) = mMovieDao.insertDetailMovie(movie)

    suspend fun insertDetailTv(tv: TvEntity) = mMovieDao.insertDetailTv(tv)

    fun setFavoriteMovie(movie: MovieEntity, newState: Boolean){
        movie.favorite = newState
        mMovieDao.updateMovie(movie)
    }

    fun setFavoriteTv(tv: TvEntity, newState: Boolean){
        tv.favorite = newState
        mMovieDao.updateTv(tv)
    }

    fun getFavoriteMovie(): DataSource.Factory<Int, MovieEntity> = mMovieDao.getFavoriteMovie()

    fun getFavoriteTv(): DataSource.Factory<Int, TvEntity> = mMovieDao.getFavoriteTv()
}