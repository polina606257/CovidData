package com.example.covidappapi.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.covidappapi.datasource.local.dao.CountriesDao
import com.example.covidappapi.datasource.local.dao.AllCasesDao
import com.example.covidappapi.model.AllCases
import com.example.covidappapi.model.CountryCases

@Database(entities = [AllCases::class, CountryCases::class], version = 1)
abstract class LocalDataSource : RoomDatabase(){
    abstract fun allCasesDao(): AllCasesDao
    abstract fun countriesDao(): CountriesDao
}