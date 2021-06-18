package com.dicoding.submissions.movies.di

import com.dicoding.submissions.movies.core.domain.usecase.MovieIntercator
import com.dicoding.submissions.movies.core.domain.usecase.MovieUseCase
import com.dicoding.submissions.movies.ui.detail_movie.DetailMovieViewModel
import com.dicoding.submissions.movies.ui.detail_tv.DetailTvViewModel
import com.dicoding.submissions.movies.ui.film.MovieViewModel
import com.dicoding.submissions.movies.ui.tv.TvViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<MovieUseCase> { MovieIntercator(get()) }
}

val viewModelModule = module {
    viewModel { MovieViewModel(get()) }
    viewModel { TvViewModel(get()) }
    viewModel { DetailMovieViewModel(get()) }
    viewModel { DetailTvViewModel(get()) }
}