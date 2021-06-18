package com.dicoding.submissions.movies.favorite.di

import com.dicoding.submissions.movies.favorite.favoritemovie.FavoriteMovieViewModel
import com.dicoding.submissions.movies.favorite.favoritetv.FavoriteTvViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoriteModule = module {
    viewModel { FavoriteMovieViewModel(get()) }
    viewModel { FavoriteTvViewModel(get()) }
}