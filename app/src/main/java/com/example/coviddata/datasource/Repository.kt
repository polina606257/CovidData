package com.example.covidappapi.datasource

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.covidappapi.datasource.local.LocalDataSource
import com.example.covidappapi.datasource.remote.RemoteDataSource
import com.example.covidappapi.model.AllCases
import com.example.covidappapi.model.CountryCases
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

/*            remoteDataSource.covidCountryLiveData.observeForever { countryCovidData ->
                localDataSource.countriesDao().insert(countryCovidData)
            }*/
        }

        val allCasesHistoryLiveData: LiveData<List<AllCases>> = localDataSource.allCasesDao().getAllCasesLiveData()
        val allcasesLastLiveData = Transformations.map(allCasesHistoryLiveData) { history ->
            history.maxByOrNull { it.datetime }
        }

/*        val covidHistoryLiveDataCountryCases: LiveData<List<CovidDataCountryCases>> = localDataSource.countriesDao().
        getCountryCasesLiveData()
        val covidLastLiveDataCountryCase = Transformations.map(covidHistoryLiveDataCountryCases) { history ->
            history.last()}*/

        val countriesLiveData = remoteDataSource.countriesLiveData
        val countryLiveData = remoteDataSource.countryLiveData

        fun refreshAllCases(){
            remoteDataSource.refreshAllCases()
        }

        fun refreshCountriesCases() {
            remoteDataSource.refreshCountriesCases()
        }

        fun refreshCountryCases(country: CountryCases) {
            remoteDataSource.refreshCountryCases(country)
        }

        fun insert(dataAllCases: AllCases){
            localDataSource.allCasesDao().insert(dataAllCases)
        }

        fun delete(dataAllCases: AllCases){
            localDataSource.allCasesDao().delete(dataAllCases)
        }
    }