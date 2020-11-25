package com.example.coviddata.datasource.remote
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.coviddata.datasource.DataResult
import com.example.coviddata.datasource.FailureResult
import com.example.coviddata.datasource.SuccessResult
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
    private val BASE_URL = "https://corona.lmao.ninja/"
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()
    val worldDataLiveData = MutableLiveData<DataResult<WorldData>>()
    val allCountriesLiveData = MutableLiveData<List<CountryData>>()
    val countryLiveData = MutableLiveData<CountryData>()

    interface ApiRemoteService {
        @GET("v3/covid-19/all")
        fun getWorldData(): Call<WorldData>

        @GET("v3/covid-19/countries")
        fun getAllCountriesData(): Call<List<CountryData>>

        @GET("v3/covid-19/countries/{country}")
        fun getCountryData(@Path ("country") country: String): Call<CountryData>
    }

    private val remoteService: ApiRemoteService by lazy {
        retrofit.create(ApiRemoteService::class.java)
    }

    fun refreshWorldData() {
        remoteService.getWorldData().enqueue(object : Callback<WorldData> {
            override fun onResponse(call: Call<WorldData>, response: Response<WorldData>) {
                Log.d("myLog", response.body().toString())
                worldDataLiveData.value = SuccessResult(response.body()!!)
            }

            override fun onFailure(call: Call<WorldData>, t: Throwable) {
                worldDataLiveData.value = FailureResult(t.message.toString())
            }
        })
    }

    fun refreshAllCountriesData() {
//        countriesLiveData.value = getTestCountries()
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

//fun getTestCountries(): List<CountryCases> = listOf(
//        CountryCases(
//                name="Russia", cases = 45654
//        ),
//        CountryCases(
//                name="USA", cases = 456
//        )
//)
