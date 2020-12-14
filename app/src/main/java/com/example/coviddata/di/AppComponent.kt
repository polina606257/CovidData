package com.example.coviddata.di

import android.content.Context
import com.example.coviddata.ui.worlddata.WorldDataFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    interface Factory{
        fun create(@BindsInstance context: Context): AppComponent
    }
    fun inject(fragment: WorldDataFragment)
}