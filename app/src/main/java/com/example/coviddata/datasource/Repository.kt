package com.example.coviddata.datasource

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.covidappapi.datasource.local.LocalDataSource
import com.example.coviddata.datasource.remote.RemoteDataSource
import com.example.coviddata.model.WorldData
import com.example.coviddata.model.CountryData
import java.time.LocalDate

class Repository (
            private val localDataSource: LocalDataSource,
            private val remoteDataSource: RemoteDataSource
){
        init {
            remoteDataSource.worldDataLiveData.observeForever { worldCovidData ->
                when (worldCovidData) {
                    is SuccessResult -> {
                        worldCovidData.data.date = LocalDate.now().toString()
                        localDataSource.worldDataDao().insert(worldCovidData.data)
                    }
                    is FailureResult->{
                        worldDataLastLiveData.value = worldCovidData
                    }
                }
            }
            localDataSource.worldDataDao().getHistoryWorldDataLiveData().observeForever {history ->
                val lastData = history.maxByOrNull { it.date }
                if (lastData != null)
                    worldDataLastLiveData.value = SuccessResult(lastData)
                else
                    worldDataLastLiveData.value = FailureResult("No data in local DB")
            }

            remoteDataSource.allCountriesLiveData.observeForever { allCountriesData ->
                for (countryData in allCountriesData) {
                    countryData.date = LocalDate.now().toString()
                    localDataSource.allCountriesDataDao().insert(countryData)
                }
            }
        }

        val worldDataHistoryLiveData: LiveData<List<WorldData>> = localDataSource.worldDataDao()
                .getHistoryWorldDataLiveData()
        val worldDataLastLiveData = MutableLiveData<DataResult<WorldData>>()
        /*val worldDataLastLiveData: LiveData<WorldData?> = Transformations.map(worldDataHistoryLiveData) { history ->
            history.maxByOrNull { it.date }
        }*/





        val allCountriesHistoryLiveData: LiveData<List<CountryData>> = localDataSource.allCountriesDataDao()
                .getHistoryAllCountriesDataLiveData()

        val allCountriesDataLastLiveData = Transformations.map(allCountriesHistoryLiveData) {history ->
                history.filter { it.date == (history.maxByOrNull { it.date })?.date.toString() }
            }

        fun refreshWorldData(){
            remoteDataSource.refreshWorldData()
        }

        fun refreshAllCountriesData() {
            remoteDataSource.refreshAllCountriesData()
        }
}