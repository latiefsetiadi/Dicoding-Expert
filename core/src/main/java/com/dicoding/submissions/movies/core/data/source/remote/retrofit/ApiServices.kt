package com.dicoding.submissions.movies.core.data.source.remote.retrofit


import com.dicoding.submissions.movies.core.data.source.remote.retrofit.datarespons.DataResponse
import com.dicoding.submissions.movies.core.data.source.remote.retrofit.datarespons.DetailResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServices {
    @GET("movie/top_rated")
    suspend fun getMovies(
            @Query("api_key") apiKey:String
    ): DataResponse

    @GET("movie/{movie_id}")
   suspend fun getDetailMovies(
            @Path("movie_id") id:String,
            @Query("api_key") apiKey: String
    ): DetailResponse

    @GET("tv/top_rated")
   suspend fun getTv(
            @Query("api_key") apiKey: String
    ): DataResponse

    @GET("tv/{tv_id}")
   suspend fun getDetailTv(
            @Path("tv_id") id: String,
            @Query("api_key") apiKey: String
    ): DetailResponse
}