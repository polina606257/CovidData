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
        return try {
            val data = remoteDataSource.getWorldData()
            data.date = LocalDate.now().toString()
            localDataSource.worldDataDao().insert(data)
            SuccessResult(data)
        } catch (e: Exception) {
            val localData = localDataSource.worldDataDao().getLastWorldData()
            if (localData != null)
                FromCacheResult(
                    localData,
                    "no data from remote database, data got from local database"
                )
            else
                FailureResult(e.message.toString())
        }
    }

    val worldDataHistoryLiveData: LiveData<List<WorldData>> = localDataSource.worldDataDao()
        .getHistoryWorldDataLiveData()


    suspend fun getAllCountriesData(): DataResult<List<CountryData>?> {
        return try {
            val countriesData = remoteDataSource.getAllCountriesData()
            for (countryData in countriesData) {
                countryData.date = LocalDate.now().toString()
                localDataSource.allCountriesDataDao().insert(countryData)
            }
            SuccessResult(countriesData)
        } catch (e: Exception) {
            val localData = localDataSource.allCountriesDataDao().getLastAllCountriesData()
            if (!localData.isEmpty())
                FromCacheResult(
                    localData,
                    "no data from remote database, data got from local database"
                )
            else
                FailureResult(e.message.toString())
        }
    }

    val allCountriesHistoryDataLiveData =
        localDataSource.allCountriesDataDao().getHistoryAllCountriesDataLiveData()

}


