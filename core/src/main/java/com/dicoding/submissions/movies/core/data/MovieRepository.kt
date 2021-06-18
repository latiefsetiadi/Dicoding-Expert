package com.dicoding.submissions.movies.core.data

import androidx.lifecycle.asFlow
import androidx.paging.*
import com.dicoding.submissions.movies.core.data.source.local.entity.MovieEntity
import com.dicoding.submissions.movies.core.data.source.local.entity.TvEntity
import com.dicoding.submissions.movies.core.data.source.local.LocalDataSource
import com.dicoding.submissions.movies.core.data.source.remote.ApiResponse
import com.dicoding.submissions.movies.core.data.source.remote.RemoteDataSource
import com.dicoding.submissions.movies.core.data.source.remote.retrofit.datarespons.DetailResponse
import com.dicoding.submissions.movies.core.data.source.remote.retrofit.datarespons.ResultsItem
import com.dicoding.submissions.movies.core.utils.AppExecutors
import com.dicoding.submissions.movies.core.data.vo.Resource
import com.dicoding.submissions.movies.core.domain.repository.IMovieRepository
import com.dicoding.submissions.movies.core.domain.model.Movie
import com.dicoding.submissions.movies.core.domain.model.Tv
import com.dicoding.submissions.movies.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MovieRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors) : IMovieRepository {


    override fun getMovies():  Flow<Resource<List<Movie>>> {
        return object : com.dicoding.submissions.movies.core.data.NetworkBoundResource<List<Movie>, List<ResultsItem>>(appExecutors){
            override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.getMovies().map { DataMapper.mapMovieEntitiesToDomain(it) }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean =
                data == null || data.isEmpty()


            override suspend fun createCall(): Flow<ApiResponse<List<ResultsItem>>> =
                remoteDataSource.getMovies()


            override suspend fun saveCallResult(data: List<ResultsItem>) {
                val movieList = ArrayList<MovieEntity>()

                for (movieRespons in data){
                    val movie = MovieEntity(movieRespons.id.toString(),
                    movieRespons.title,
                    movieRespons.overview,
                    "",
                    movieRespons.releaseDate,
                    movieRespons.posterPath,
                    false)
                    movieList.add(movie)
                }
                localDataSource.insertMovie(movieList)
            }

        }.asFlow()
    }


   override fun getTv(): Flow<Resource<List<Tv>>> {
        return object : com.dicoding.submissions.movies.core.data.NetworkBoundResource<List<Tv>, List<ResultsItem>>(appExecutors){
            override fun loadFromDB(): Flow<List<Tv>> {
                return localDataSource.getTv().map { DataMapper.mapTvEntitesToDomain(it) }
            }

            override fun shouldFetch(data: List<Tv>?): Boolean {
                return data == null || data.isEmpty()
            }

            override suspend fun createCall(): Flow<ApiResponse<List<ResultsItem>>> {
                return remoteDataSource.getTv()
            }

            override suspend fun saveCallResult(data: List<ResultsItem>) {
                val tvList = ArrayList<TvEntity>()
                for (tvRespons in data){
                    val tvEntity = TvEntity(tvRespons.id.toString(),
                        tvRespons.name,
                        tvRespons.overview,
                        "",
                        tvRespons.firstAirDate,
                        tvRespons.posterPath,false
                    )
                    tvList.add(tvEntity)
                }

                localDataSource.insertTv(tvList)
            }
        }.asFlow()
    }


   override fun getDetailMovies(movieId: String): Flow<Resource<Movie>> {
        return object : com.dicoding.submissions.movies.core.data.NetworkBoundResource<Movie, DetailResponse>(appExecutors){
            override fun loadFromDB(): Flow<Movie> {
                return localDataSource.getDetailMovies(movieId).map { DataMapper.mapDetailMovieEntitiesToDomain(it) }
            }

            override fun shouldFetch(data: Movie?): Boolean {
                return data?.genre == null || data.genre.isEmpty()
            }

            override suspend fun createCall(): Flow<ApiResponse<DetailResponse>> {
                return remoteDataSource.getDetailMovies(movieId)
            }

            override suspend fun saveCallResult(data: DetailResponse) {
                val genre = StringBuilder()
                for (it in data.genres){
                    if (data.genres.indexOf(it) == data.genres.lastIndex)genre.append("${it.name}.")
                    else genre.append("${it.name}, ")
                }

                val movieEntity = MovieEntity(movieId,data.title,data.overview,genre.toString(),data.releaseDate,data.posterPath)
                localDataSource.insertDetailMovie(movieEntity)
            }
        }.asFlow()
    }


   override fun getDetailTv(tvId: String): Flow<Resource<Tv>> {
        return object : com.dicoding.submissions.movies.core.data.NetworkBoundResource<Tv, DetailResponse>(appExecutors){
            override fun loadFromDB(): Flow<Tv> {
                return localDataSource.getDetailTv(tvId).map { DataMapper.mapDetailTvEntitiesToDomain(it) }
            }

            override fun shouldFetch(data: Tv?): Boolean {
                return data?.genre == null || data.genre.isEmpty()
            }

            override suspend fun createCall(): Flow<ApiResponse<DetailResponse>> {
                return remoteDataSource.getDetailTv(tvId)
            }

            override suspend fun saveCallResult(data: DetailResponse) {
                val genres = StringBuilder()
                data.genres.forEach {
                    if (data.genres.indexOf(it) == data.genres.lastIndex) genres.append("${it.name}.")
                    else genres.append("${it.name},")
                }

                val tvEntity = TvEntity(tvId,data.name,data.overview,genres.toString(),data.firstAirDate,data.posterPath)
                localDataSource.insertDetailTv(tvEntity)
            }

        }.asFlow()
    }

   override fun getFavoriteMovie(): Flow<PagedList<Movie>> {
        val configur = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(6)
            .setPageSize(6)
            .build()
        return LivePagedListBuilder( DataMapper.mapPagedListMovieEntityToDomain(localDataSource.getFavoriteMovie()),configur).build().asFlow()
    }

   override fun getFavoriteTv(): Flow<PagedList<Tv>> {
        val configur = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(6)
            .setPageSize(6)
            .build()
        return LivePagedListBuilder(DataMapper.mapPagedListTvEntityToDomain(localDataSource.getFavoriteTv()),configur).build().asFlow()
    }

   override fun setFavoriteMovie(movie: Movie, state: Boolean) {
        val movieEntity = DataMapper.mapDomainToMovieEntity(movie)
        appExecutors.diskIO().execute { localDataSource.setFavoriteMovie(movieEntity,state) }
    }

   override fun setFavoriteTv(tv: Tv, state: Boolean) {
        val tvEntity = DataMapper.mapDomainToTvEntity(tv)
        appExecutors.diskIO().execute { localDataSource.setFavoriteTv(tvEntity,state) }
    }
}