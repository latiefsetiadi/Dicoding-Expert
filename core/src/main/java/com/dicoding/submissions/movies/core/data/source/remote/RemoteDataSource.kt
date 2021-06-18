package com.dicoding.submissions.movies.core.data.source.remote


import android.util.Log
import com.dicoding.submissions.movies.core.BuildConfig
import com.dicoding.submissions.movies.core.data.source.remote.retrofit.ApiServices
import com.dicoding.submissions.movies.core.data.source.remote.retrofit.datarespons.DetailResponse
import com.dicoding.submissions.movies.core.data.source.remote.retrofit.datarespons.ResultsItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiServices){

    companion object{
        private const val API_KEY = BuildConfig.API_KEY
    }


    suspend fun getMovies(): Flow<ApiResponse<List<ResultsItem>>>{
        return flow {
            try {
                val response = apiService.getMovies(API_KEY)
                val dataArray = response.results
                if (dataArray.isNotEmpty()){
                    emit(ApiResponse.success(response.results))
                }else{
                    emit(ApiResponse.empty)
                }
            }catch (e : Exception){
                emit(ApiResponse.error(e.toString()))
                Log.e("RemoteDataSource",e.toString())
            }
        } .flowOn(Dispatchers.IO)
    }


    fun getTv(): Flow<ApiResponse<List<ResultsItem>>>{
        return flow {
            try {
                val response = apiService.getTv(API_KEY)
                val dataArray = response.results
                if (dataArray.isNotEmpty()){
                    emit(ApiResponse.success(response.results))
                }else{
                    emit(ApiResponse.empty)
                }
            }catch (e : Exception){
                emit(ApiResponse.error(e.toString()))
                Log.e("RemoteDataSource",e.toString())
            }
        } .flowOn(Dispatchers.IO)
    }


    fun getDetailMovies(movieId: String): Flow<ApiResponse<DetailResponse>>{
        return flow {
            try {
                val response = apiService.getDetailMovies(movieId,API_KEY)
                emit(ApiResponse.success(response))
            }catch (e : Exception){
                emit(ApiResponse.error(e.toString()))
                Log.e("RemoteDataSource",e.toString())
            }
        } .flowOn(Dispatchers.IO)
    }

    fun getDetailTv(tvId: String): Flow<ApiResponse<DetailResponse>>{
        return flow {
            try {
                val response = apiService.getDetailTv(tvId,API_KEY)
                emit(ApiResponse.success(response))
            }catch (e : Exception){
                emit(ApiResponse.error(e.toString()))
                Log.e("RemoteDataSource",e.toString())
            }
        } .flowOn(Dispatchers.IO)
    }
}