package com.example.covidappapi.datasource.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.coviddata.model.CountryData

@Dao
interface AllCountriesDataDao {
    @Query("SELECT * FROM CountryData")
    fun getHistoryAllCountriesDataLiveData(): LiveData<List<CountryData>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(countryData: CountryData)

    @Delete
    fun delete(countryData: CountryData)
}