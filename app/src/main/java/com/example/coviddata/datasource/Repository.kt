package com.example.covidappapi.datasource

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.covidappapi.datasource.local.LocalDataSource
import com.example.covidappapi.datasource.remote.RemoteDataSource
import com.example.covidappapi.model.AllCases
import java.time.LocalDateTime

    class Repository (
            private val localDataSource: LocalDataSource,
            private val remoteDataSource: RemoteDataSource)
    {
        init {
            remoteDataSource.allCasesLiveData.observeForever { allCovidData ->
                allCovidData.datetime = LocalDateTime.now().toString()
                localDataSource.allCasesDao().insert(allCovidData)
            }
        }

        val allCasesHistoryLiveData: LiveData<List<AllCases>> = localDataSource.allCasesDao().getAllCasesLiveData()
        val allcasesLastLiveData = Transformations.map(allCasesHistoryLiveData) { history ->
            history.maxByOrNull { it.datetime }
        }

        val countriesLiveData = remoteDataSource.countriesLiveData

        fun refreshAllCases(){
            remoteDataSource.refreshAllCases()
        }

        fun refreshCountriesCases() {
            remoteDataSource.refreshCountriesCases()
        }

        fun insert(dataAllCases: AllCases){
            localDataSource.allCasesDao().insert(dataAllCases)
        }

        fun delete(dataAllCases: AllCases){
            localDataSource.allCasesDao().delete(dataAllCases)
        }
    }