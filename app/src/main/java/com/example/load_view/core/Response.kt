package com.example.load_view.core

sealed class Response<T> {

    class Loading<T> : Response<T>()
    data class Success<T>(val value: T): Response<T>()
    data class Error<T>(val exception: Exception): Response<T>()

    companion object {
        fun <T> loading() = Loading<T>()
        fun <T> success(data: T): Response<T> =
            Success(data)
        fun <T> error(e: Exception): Response<T> =
            Error(e)
    }
}
