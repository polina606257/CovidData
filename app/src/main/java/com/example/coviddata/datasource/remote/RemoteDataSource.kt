package com.example.covidappapi.datasource.remote
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.covidappapi.model.AllCases
import com.example.covidappapi.model.CountryCases
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

class RemoteDataSource {
    private val BASE_URL = "https://corona.lmao.ninja/"
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()
    val allCasesLiveData = MutableLiveData<AllCases>()
    val countriesLiveData = MutableLiveData<List<CountryCases>>()
    val countryLiveData = MutableLiveData<CountryCases>()

    interface ApiRemoteService {
        @GET("v3/covid-19/all")
        fun getAllCases(): Call<AllCases>

        @GET("v3/covid-19/countries")
        fun getCountriesCases(): Call<List<CountryCases>>

        @GET("v3/covid-19/countries/{country}")
        fun getCountryCases(@Path ("country") country: String): Call<CountryCases>
    }

    private val remoteService: ApiRemoteService by lazy {
        retrofit.create(ApiRemoteService::class.java)
    }

    fun refreshAllCases() {
        remoteService.getAllCases().enqueue(object : Callback<AllCases> {
            override fun onResponse(call: Call<AllCases>, response: Response<AllCases>) {
                allCasesLiveData.value = response.body()!!
            }

            override fun onFailure(call: Call<AllCases>, t: Throwable) {
                Log.v("MyLogAllCases", t.message.toString())
            }
        })
    }

    fun refreshCountriesCases() {
        remoteService.getCountriesCases().enqueue(object : Callback<List<CountryCases>> {
            override fun onResponse(call: Call<List<CountryCases>>,
                                    response: Response<List<CountryCases>>) {
                Log.d("myLogCountries", response.body().toString())
                countriesLiveData.value = response.body()!!
            }

            override fun onFailure(call: Call<List<CountryCases>>, t: Throwable) {
                Log.d("myLogCountriesCases", t.message.toString())
            }
        })
    }

    fun refreshCountryCases(country: CountryCases) {
        remoteService.getCountryCases(country.name).enqueue(object : Callback<CountryCases> {
            override fun onResponse(call: Call<CountryCases>,
                                    response: Response<CountryCases>) {
                countryLiveData.value = response.body()!!
            }

            override fun onFailure(call: Call<CountryCases>, t: Throwable) {
                Log.d("myLogCountryCases", t.message.toString())
            }
        })
    }
}
