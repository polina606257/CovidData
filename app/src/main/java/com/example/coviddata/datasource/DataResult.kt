package com.example.coviddata.datasource

sealed class DataResult<out R>
data class SuccessResult<out T>(val data: T) : DataResult<T>()
data class FailureResult(val exception: String) : DataResult<Nothing>()
data class FromCacheResult<out T>(val data: T, val message: String): DataResult<T>()