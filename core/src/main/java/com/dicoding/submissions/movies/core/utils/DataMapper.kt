package com.dicoding.submissions.movies.core.utils

import androidx.paging.DataSource
import com.dicoding.submissions.movies.core.data.source.local.entity.MovieEntity
import com.dicoding.submissions.movies.core.data.source.local.entity.TvEntity
import com.dicoding.submissions.movies.core.domain.model.Movie
import com.dicoding.submissions.movies.core.domain.model.Tv

object DataMapper {

    fun mapMovieEntitiesToDomain(input: List<MovieEntity>): List<Movie> =
        input.map {
            Movie(
                movieId = it.movieId,
                title = it.title,
                description = it.description,
                genre = it.genre,
                date = it.date,
                image = it.image,
                favorite = it.favorite
            )
        }
    fun mapDetailMovieEntitiesToDomain(input: MovieEntity) = Movie(
            movieId = input.movieId,
            title = input.title,
            description = input.description,
            genre = input.genre,
            date = input.date,
            image = input.image,
            favorite = input.favorite
            )
    fun mapDetailTvEntitiesToDomain(input: TvEntity) = Tv(
            tvId = input.tvId,
            title = input.title,
            description = input.description,
            genre = input.genre,
            date = input.date,
            image = input.image,
            favorite = input.favorite
    )


    fun mapTvEntitesToDomain(input: List<TvEntity>): List<Tv> =
        input.map {
            Tv(
                tvId = it.tvId,
                title = it.title,
                description = it.description,
                genre = it.genre,
                date = it.date,
                image = it.image,
                favorite = it.favorite
            )
        }
    fun mapPagedListMovieEntityToDomain(input: DataSource.Factory<Int,MovieEntity>): DataSource.Factory<Int,Movie> =
        input.map {
            Movie(
                movieId = it.movieId,
                title = it.title,
                description = it.description,
                genre = it.genre,
                date = it.date,
                image = it.image,
                favorite = it.favorite
            )
        }

    fun mapPagedListTvEntityToDomain(input: DataSource.Factory<Int,TvEntity>): DataSource.Factory<Int,Tv> =
        input.map {
            Tv(
                tvId = it.tvId,
                title = it.title,
                description = it.description,
                genre = it.genre,
                date = it.date,
                image = it.image,
                favorite = it.favorite
            )
        }

    fun mapDomainToMovieEntity(input: Movie) = MovieEntity(
        movieId = input.movieId,
        title = input.title,
        description = input.description,
        genre = input.genre,
        date = input.date,
        image = input.image,
        favorite = input.favorite
    )

    fun mapDomainToTvEntity(input: Tv) = TvEntity(
        tvId = input.tvId,
        title = input.title,
        description = input.description,
        genre = input.genre,
        date = input.date,
        image = input.image,
        favorite = input.favorite
    )
}