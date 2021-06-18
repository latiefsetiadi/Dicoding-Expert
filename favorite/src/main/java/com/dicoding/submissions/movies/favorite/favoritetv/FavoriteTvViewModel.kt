package com.dicoding.submissions.movies.favorite.favoritetv

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dicoding.submissions.movies.core.domain.usecase.MovieUseCase

class FavoriteTvViewModel(movieUseCase: MovieUseCase): ViewModel() {

    val getFavoriteTv = movieUseCase.getFavoriteTv().asLiveData()
}