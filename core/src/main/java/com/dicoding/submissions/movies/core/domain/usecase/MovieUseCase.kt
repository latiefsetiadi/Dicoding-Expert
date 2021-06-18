package com.dicoding.submissions.movies.core.domain.usecase

import androidx.paging.PagedList
import com.dicoding.submissions.movies.core.data.vo.Resource
import com.dicoding.submissions.movies.core.domain.model.Movie
import com.dicoding.submissions.movies.core.domain.model.Tv
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {
    fun getMovies(): Flow<Resource<List<Movie>>>

    fun getTv(): Flow<Resource<List<Tv>>>

    fun getDetailMovies(movieId: String): Flow<Resource<Movie>>

    fun getDetailTv(tvId: String): Flow<Resource<Tv>>

    fun getFavoriteMovie(): Flow<PagedList<Movie>>

    fun getFavoriteTv(): Flow<PagedList<Tv>>

    fun setFavoriteMovie(movie: Movie, state: Boolean)

    fun setFavoriteTv(tv: Tv, state: Boolean)
}