package com.example.coviddata.di

import android.content.Context
import androidx.room.Room
import com.example.coviddata.datasource.local.LocalDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object AppModule {

    @Singleton
    @Provides
    fun provideLocalDataSource(context: Context): LocalDataSource {
        return Room.databaseBuilder(context, LocalDataSource::class.java, "db").build()
    }
}