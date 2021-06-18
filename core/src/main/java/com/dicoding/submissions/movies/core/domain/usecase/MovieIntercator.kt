package com.dicoding.submissions.movies.core.domain.usecase

import androidx.paging.PagedList
import com.dicoding.submissions.movies.core.data.vo.Resource
import com.dicoding.submissions.movies.core.domain.model.Movie
import com.dicoding.submissions.movies.core.domain.model.Tv
import com.dicoding.submissions.movies.core.domain.repository.IMovieRepository
import kotlinx.coroutines.flow.Flow

class MovieIntercator(private val movieRepository: IMovieRepository): MovieUseCase {
    override fun getMovies(): Flow<Resource<List<Movie>>> = movieRepository.getMovies()

    override fun getTv(): Flow<Resource<List<Tv>>> = movieRepository.getTv()

    override fun getDetailMovies(movieId: String): Flow<Resource<Movie>> = movieRepository.getDetailMovies(movieId)

    override fun getDetailTv(tvId: String): Flow<Resource<Tv>> = movieRepository.getDetailTv(tvId)

    override fun getFavoriteMovie(): Flow<PagedList<Movie>> = movieRepository.getFavoriteMovie()

    override fun getFavoriteTv(): Flow<PagedList<Tv>> = movieRepository.getFavoriteTv()

    override fun setFavoriteMovie(movie: Movie, state: Boolean) = movieRepository.setFavoriteMovie(movie,state)

    override fun setFavoriteTv(tv: Tv, state: Boolean) = movieRepository.setFavoriteTv(tv,state)
}