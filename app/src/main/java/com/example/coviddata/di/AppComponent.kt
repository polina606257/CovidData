package com.example.coviddata.di

import android.content.Context
import com.example.coviddata.ui.allcountriesdata.AllCountriesDataFragment
import com.example.coviddata.ui.countrydata.CountryDataFragment
import com.example.coviddata.ui.countrydata.history.CountryDataHistoryFragment
import com.example.coviddata.ui.map.MapsFragment
import com.example.coviddata.ui.worlddata.WorldDataFragment
import com.example.coviddata.ui.worlddata.history.WorldDataHistoryFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }


    fun inject(fragment: WorldDataFragment)
    fun inject(fragment: WorldDataHistoryFragment)
    fun inject(fragment: AllCountriesDataFragment)
    fun inject(fragment: CountryDataFragment)
    fun inject(fragment: CountryDataHistoryFragment)
    fun inject(fragment: MapsFragment)
}