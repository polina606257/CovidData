package com.example.coviddata.datasource.remote

import com.example.coviddata.model.WorldData
import com.example.coviddata.model.CountryData
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

class RemoteDataSource {
    private val BASE_URL = "https://corona.lmao.ninja/"
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    private interface ApiRemoteService {
        @GET("v3/covid-19/all")
        suspend fun getWorldData(): WorldData

        @GET("v3/covid-19/countries")
        suspend fun getAllCountriesData(): List<CountryData>

        @GET("v3/covid-19/countries/{country}")
        suspend fun getCountryData(@Path ("country") country: String): CountryData
    }

    private val remoteService: ApiRemoteService by lazy {
        retrofit.create(ApiRemoteService::class.java)
    }

    suspend fun getWorldData(): WorldData = remoteService.getWorldData()

    suspend fun getAllCountriesData() = remoteService.getAllCountriesData()

    suspend fun getCountryData(country: String): CountryData = remoteService.getCountryData(country)
}

