package com.example.covidappapi.datasource

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.covidappapi.datasource.local.LocalDataSource
import com.example.covidappapi.datasource.remote.RemoteDataSource
import com.example.coviddata.model.CountryData
import com.example.coviddata.model.WorldData
import java.time.LocalDate

class Repository(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) {

    suspend fun getWorldData(): WorldData?{
        try {
            val data = remoteDataSource.getWorldData()
            localDataSource.worldDataDao().insert(data)
            return data
        }catch (e: Exception){
            return localDataSource.worldDataDao().getLastWorldData()
        }
    }





















    val worldDataHistoryLiveData: LiveData<List<WorldData>> = localDataSource.worldDataDao()
        .getHistoryWorldDataLiveData()

    init {


        remoteDataSource.allCountriesLiveData.observeForever { allCountriesData ->
            for (countryData in allCountriesData) {
                countryData.date = LocalDate.now().toString()
                localDataSource.allCountriesDataDao().insert(countryData)
            }
        }
    }

    val allCountriesHistoryLiveData: LiveData<List<CountryData>> =
        localDataSource.allCountriesDataDao()
            .getHistoryAllCountriesDataLiveData()

    val allCountriesDataLastLiveData = Transformations.map(allCountriesHistoryLiveData) { history ->
        history.filter { it.date == (history.maxByOrNull { it.date })?.date.toString() }
    }


    fun refreshAllCountriesData() {
        remoteDataSource.refreshAllCountriesData()
    }
}