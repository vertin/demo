package com.check.demo.datasource

sealed class DataResultWrapper<T, E>(
    val data: T? = null,
    val err: E? = null
) {

    data class Error<T, E>(val error: E) : DataResultWrapper<T, E>(err = error)
    data class Success<T, E>(val dataResult: T) : DataResultWrapper<T, E>(data = dataResult)
}
