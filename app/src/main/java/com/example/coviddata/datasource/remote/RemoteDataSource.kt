package com.example.covidappapi.datasource.remote
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.coviddata.model.WorldData
import com.example.coviddata.model.CountryData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

class RemoteDataSource {
    private val BASE_URL = "https://corona.lmao.ninja1/"
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()


    private interface ApiRemoteService {
        @GET("v3/covid-19/all")
        suspend fun getWorldData(): WorldData

        @GET("v3/covid-19/countries")
        fun getAllCountriesData(): Call<List<CountryData>>

        @GET("v3/covid-19/countries/{country}")
        fun getCountryData(@Path ("country") country: String): Call<CountryData>
    }

    private val remoteService: ApiRemoteService by lazy {
        retrofit.create(ApiRemoteService::class.java)
    }

    suspend fun getWorldData(): WorldData = remoteService.getWorldData()








    val allCountriesLiveData = MutableLiveData<List<CountryData>>()
    val countryLiveData = MutableLiveData<CountryData>()
    fun refreshAllCountriesData() {
       remoteService.getAllCountriesData().enqueue(object : Callback<List<CountryData>> {
            override fun onResponse(call: Call<List<CountryData>>,
                                    response: Response<List<CountryData>>) {
                Log.d("myLogCountries", response.body().toString())
                allCountriesLiveData.value = response.body()!!
            }

            override fun onFailure(call: Call<List<CountryData>>, t: Throwable) {
                Log.d("myLogCountriesCases", t.message.toString())
            }
        })
    }

    fun refreshCountryData(country: CountryData) {
        remoteService.getCountryData(country.name).enqueue(object : Callback<CountryData> {
            override fun onResponse(call: Call<CountryData>,
                                    response: Response<CountryData>) {
                countryLiveData.value = response.body()!!
            }

            override fun onFailure(call: Call<CountryData>, t: Throwable) {
                Log.d("myLogCountryCases", t.message.toString())
            }
        })
    }
}

