package com.example.covidappapi.datasource.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.covidappapi.model.CountryCases

@Dao
interface CountriesDao {
    @Query("SELECT * FROM CountryCases")
    fun getCountryCasesLiveData(): LiveData<List<CountryCases>>

    @Insert
    fun insert(countryData: CountryCases)

    @Delete
    fun delete(countryData: CountryCases)
}