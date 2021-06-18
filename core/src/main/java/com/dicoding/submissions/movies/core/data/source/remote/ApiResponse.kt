package com.dicoding.submissions.movies.core.data.source.remote


sealed class ApiResponse<out R>{

    data class success<out T>(val data: T) : ApiResponse<T>()
    data class error(val errorMessage: String) : ApiResponse<Nothing>()
    object empty : ApiResponse<Nothing>()
}