package com.dicoding.submissions.movies.ui.film

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dicoding.submissions.movies.core.domain.usecase.MovieUseCase

class MovieViewModel(movieUseCase: MovieUseCase) : ViewModel() {

    val getMovies = movieUseCase.getMovies().asLiveData()
}