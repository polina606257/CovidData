package com.example.coviddata.datasource

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


//    init {
//
//
//        remoteDataSource.allCountriesLiveData.observeForever { allCountriesData ->
//            for (countryData in allCountriesData) {
//                countryData.date = LocalDate.now().toString()
//                localDataSource.allCountriesDataDao().insert(countryData)
//            }
//        }
//    }
//
//
//    fun refreshAllCountriesData() {
//        remoteDataSource.refreshAllCountriesData()
//    }
//}
//
//
//    val allCountriesHistoryLiveData: LiveData<List<CountryData>> =
//        localDataSource.allCountriesDataDao()
//            .getHistoryAllCountriesDataLiveData()
//
//    val allCountriesDataLastLiveData = Transformations.map(allCountriesHistoryLiveData) { history ->
//        history.filter { it.date == (history.maxByOrNull { it.date })?.date.toString() }
//    }
}


