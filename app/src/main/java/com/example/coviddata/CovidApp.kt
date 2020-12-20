package com.example.coviddata

import android.app.Application
import androidx.room.Room
import com.example.coviddata.datasource.Repository
import com.example.coviddata.datasource.local.LocalDataSource
import com.example.coviddata.datasource.remote.RemoteDataSource
import com.example.coviddata.di.AppComponent
import com.example.coviddata.di.DaggerAppComponent

class CovidApp : Application() {
    companion object{
        lateinit var repository: Repository
    }
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.factory().create(applicationContext)


        val remoteDataSource = RemoteDataSource()
        val localDataSource = Room.databaseBuilder(applicationContext, LocalDataSource::class.java, "db")
            .build()
        repository = Repository(localDataSource, remoteDataSource)
    }
}