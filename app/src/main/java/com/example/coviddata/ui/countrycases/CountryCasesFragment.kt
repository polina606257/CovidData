package com.example.coviddata.ui.countrycases

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.coviddata.CovidApp
import com.example.coviddata.R
import kotlinx.android.synthetic.main.fragment_cases_country.*

class CountryCasesFragment(id: Int) : Fragment() {
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_cases_country, container, false)
        CovidApp.repository.countryLiveData.observe(viewLifecycleOwner) { countryData ->
            if (countryData != null) {
                country_textView.text = "${countryData.name}"
                total_number_textView.text = "${countryData.cases}"
                deaths_number_textView.text = "${countryData.deaths}"
                recovered_number_textView.text = "${countryData.recovered}"
                cases_per_one_million_number_textView.text = "${countryData.casesPerOneMillion}"
                deaths_per_one_million_number_textView.text = "${countryData.deathsPerOneMillion}"
                tests_per_one_million_number_textView.text = "${countryData.deathsPerOneMillion}"
            } else {
                country_textView.text = getText(R.string.no_data_text)
                total_number_textView.text = getText(R.string.no_data_text)
                deaths_number_textView.text = getText(R.string.no_data_text)
                recovered_number_textView.text = getText(R.string.no_data_text)
                cases_per_one_million_number_textView.text = getText(R.string.no_data_text)
                deaths_per_one_million_number_textView.text = getText(R.string.no_data_text)
                tests_per_one_million_number_textView.text = getText(R.string.no_data_text)
            }
        }
        return root
    }

}
