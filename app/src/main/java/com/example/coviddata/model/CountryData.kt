package com.example.coviddata.model

import androidx.room.*
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

@Entity(primaryKeys = ["date","name"])
data class CountryData(
        var date: String,
        @SerializedName(value = "country")
        val name: String,
        @Embedded(prefix = "countryInfo")
        val countryInfo: CountryInfo = CountryInfo(""),
        val cases: Int,
        val deaths: Int = 0,
        val recovered: Int = 0,
        val casesPerOneMillion: Double = 0.0,
        val deathsPerOneMillion: Double = 0.0,
        val testsPerOneMillion: Double = 0.0
)/*{
        @TypeConverters(HistoryTypeConverter::class)
        var history: List<CountryData> = emptyList()
}
*/
/*@TypeConverter
class HistoryTypeConverter{
        fun fromDB(json: String): List<CountryData>{
                Gson().fromJson<List<CountryData>>(json)
        }
        fun toDB(history: List<CountryData>): String{
                return Gson().toJson(history)
        }
}*/

data class CountryInfo(
        val flag: String
)
