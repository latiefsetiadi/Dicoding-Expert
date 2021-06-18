package com.dicoding.submissions.movies.favorite.favoritemovie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dicoding.submissions.movies.core.domain.usecase.MovieUseCase

class FavoriteMovieViewModel(movieUseCase: MovieUseCase) : ViewModel() {

    val getFavoriteMovies = movieUseCase.getFavoriteMovie().asLiveData()
}