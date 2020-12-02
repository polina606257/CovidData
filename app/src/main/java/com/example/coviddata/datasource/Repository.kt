package com.example.coviddata.datasource

import androidx.lifecycle.LiveData
import com.example.covidappapi.datasource.local.LocalDataSource
import com.example.coviddata.datasource.remote.RemoteDataSource
import com.example.coviddata.model.CountryData
import com.example.coviddata.model.WorldData
import java.time.LocalDate

class Repository(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) {

    suspend fun getWorldData(): DataResult<WorldData?> {
        try {
            val data = remoteDataSource.getWorldData()
            data.date = LocalDate.now().toString()
            localDataSource.worldDataDao().insert(data)
            return SuccessResult(data)
        } catch (e: Exception) {
            val localData = localDataSource.worldDataDao().getLastWorldData()
            if (localData != null)
                return FromCacheResult(localData,
                    "no data from remote database, data got from local database")
            else
                return FailureResult(e.message.toString())
        }
    }

    val worldDataHistoryLiveData: LiveData<List<WorldData>> = localDataSource.worldDataDao()
        .getHistoryWorldDataLiveData()


    suspend fun getAllCountriesData(): DataResult<List<CountryData>?> {
        try {
            val countriesData = remoteDataSource.getAllCountriesData()
            val _countriesList: MutableList<CountryData>? = mutableListOf()
            val countriesList:List<CountryData>? = _countriesList
            for (countryData in countriesData) {
                countryData.date = LocalDate.now().toString()
                localDataSource.allCountriesDataDao().insert(countryData)
                _countriesList?.add(countryData)
            }
            return SuccessResult(countriesList)
        } catch (e: Exception) {
            val localData = localDataSource.allCountriesDataDao().getLastAllCountriesData()
            if(!localData.isEmpty())
                return FromCacheResult(localData,
                    "no data from remote database, data got from local database")
            else
                return FailureResult(e.message.toString())
            }
        }

    val allCountriesHistoryDataLiveData = localDataSource.allCountriesDataDao().getHistoryAllCountriesDataLiveData()

}


