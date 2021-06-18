package com.dicoding.submissions.movies.ui.detail_movie

import androidx.lifecycle.*
import com.dicoding.submissions.movies.core.data.vo.Resource
import com.dicoding.submissions.movies.core.domain.model.Movie
import com.dicoding.submissions.movies.core.domain.usecase.MovieUseCase

class DetailMovieViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {
    private val movieId = MutableLiveData<String>()

    fun setSelectedIdMovie(movieId: String){
        this.movieId.value = movieId
    }

    var movieDetail : LiveData<Resource<Movie>> = Transformations.switchMap(movieId){ mMovieId ->
        movieUseCase.getDetailMovies(mMovieId).asLiveData()
    }

    fun setFavoriteMovie(){
        val movieDetail = movieDetail.value
        if (movieDetail != null){

            val movie = movieDetail.data
            if (movie != null){
                val newState = !movie.favorite
                movieUseCase.setFavoriteMovie(movie, newState)
            }
        }
    }
}