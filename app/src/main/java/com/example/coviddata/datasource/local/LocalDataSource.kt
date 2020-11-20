package com.example.covidappapi.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.covidappapi.datasource.local.dao.AllCountriesDataDao
import com.example.covidappapi.datasource.local.dao.WorldDataDao
import com.example.covidappapi.model.WorldData
import com.example.coviddata.model.CountryData

@Database(entities = [WorldData::class, CountryData::class], version = 1)
abstract class LocalDataSource : RoomDatabase(){
    abstract fun allCasesDao(): WorldDataDao
    abstract fun countriesDao(): AllCountriesDataDao
}