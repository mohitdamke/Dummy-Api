package com.example.dummyapi.data

sealed class Result<T>(val data: T? = null, val message: String? = null) {
     class Success<T>(data: T?) : Result<T>(data)
     class Failed<T>(data: T? = null, message: String?) : Result<T>(data, message)
}