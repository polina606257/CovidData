package com.example.coviddata.datasource.local

import androidx.lifecycle.LiveData
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.coviddata.datasource.local.dao.AllCountriesDataDao
import com.example.coviddata.datasource.local.dao.WorldDataDao
import com.example.coviddata.model.WorldData
import com.example.coviddata.model.CountryData

@Database(entities = [WorldData::class, CountryData::class], version = 1)
abstract class LocalDataSource : RoomDatabase(){
    abstract fun worldDataDao(): WorldDataDao
    abstract fun allCountriesDataDao(): AllCountriesDataDao
}