package com.example.coviddata

import android.app.Application
import androidx.room.Room
import com.example.covidappapi.datasource.Repository
import com.example.covidappapi.datasource.local.LocalDataSource
import com.example.covidappapi.datasource.remote.RemoteDataSource

class CovidApp : Application() {
    companion object{
        lateinit var repository: Repository
    }

    override fun onCreate() {
        super.onCreate()
        val remoteDataSource = RemoteDataSource()
        val localDataSource = Room.databaseBuilder(applicationContext, LocalDataSource::class.java, "db")
            .build()
        repository = Repository(localDataSource, remoteDataSource)
    }
}