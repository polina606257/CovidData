package com.example.coviddata.datasource.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.coviddata.model.CountryData

@Dao
interface AllCountriesDataDao {
    @Query("SELECT * FROM CountryData")
    fun getHistoryAllCountriesDataLiveData(): LiveData<List<CountryData>>

    @Query("SELECT * FROM CountryData")
    suspend fun getHistoryAllCountriesData(): List<CountryData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(countryData: CountryData)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(countries: List<CountryData>)

    @Delete
    suspend fun delete(countryData: CountryData)

    suspend fun getLastAllCountriesData() = getHistoryAllCountriesData().filter { it.date ==
            (getHistoryAllCountriesData().maxByOrNull { it.date })?.date.toString() }

}