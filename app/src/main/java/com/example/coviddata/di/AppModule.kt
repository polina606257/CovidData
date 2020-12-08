package com.example.coviddata.di

import android.content.Context
import androidx.room.Room
import com.example.coviddata.datasource.local.LocalDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideLocalDataSource(@ApplicationContext context: Context): LocalDataSource {
        return Room.databaseBuilder(context, LocalDataSource::class.java, "db").build()
    }
}