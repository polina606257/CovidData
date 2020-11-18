package com.example.covidappapi.datasource

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.covidappapi.datasource.local.LocalDataSource
import com.example.covidappapi.datasource.remote.RemoteDataSource
import com.example.covidappapi.model.WorldData
import com.example.coviddata.model.CountryData
import java.time.LocalDate

class Repository (
            private val localDataSource: LocalDataSource,
            private val remoteDataSource: RemoteDataSource)
    {
        init {
            remoteDataSource.allCasesLiveData.observeForever { allCovidData ->
                allCovidData.date = LocalDate.now().toString()
                localDataSource.allCasesDao().insert(allCovidData)
            }
        }

        val worldDataHistoryLiveData: LiveData<List<WorldData>> = localDataSource.allCasesDao().getAllCasesLiveData()
        val allcasesLastLiveData = Transformations.map(worldDataHistoryLiveData) { history ->
            history.maxByOrNull { it.date }
        }

        private val _countriesLiveData = remoteDataSource.countriesLiveData
        val countriesLiveData:LiveData<List<CountryData>> = _countriesLiveData

        fun refreshAllCases(){
            remoteDataSource.refreshAllCases()
        }

        fun refreshCountriesCases() {
            remoteDataSource.refreshCountriesCases()
        }

        fun insert(dataWorldData: WorldData){
            localDataSource.allCasesDao().insert(dataWorldData)
        }

        fun delete(dataWorldData: WorldData){
            localDataSource.allCasesDao().delete(dataWorldData)
        }
    }