package com.dicoding.submissions.movies.ui.tv

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dicoding.submissions.movies.core.domain.usecase.MovieUseCase

class TvViewModel(movieUseCase: MovieUseCase) : ViewModel() {

    val getTv= movieUseCase.getTv().asLiveData()

}