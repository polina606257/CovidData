package com.example.covidappapi.datasource.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.coviddata.model.CountryData

@Dao
interface AllCountriesDataDao {
    @Query("SELECT * FROM CountryData")
    fun getCountryCasesLiveData(): LiveData<List<CountryData>>

    @Insert
    fun insert(countryData: CountryData)

    @Delete
    fun delete(countryData: CountryData)
}