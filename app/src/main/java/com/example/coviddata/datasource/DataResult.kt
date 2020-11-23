package com.example.coviddata.datasource

import com.example.coviddata.model.CountryData
import com.example.coviddata.model.WorldData

//data class DataResult1<T> (val isSuccess: Boolean, val data: T? = null, val exception: String? = null)


sealed class DataResult<out R>
data class SuccessResult<out T>(val data: T) : DataResult<T>()
data class FailureResult(val exception: String) : DataResult<Nothing>()
