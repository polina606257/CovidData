package com.example.coviddata.ui.countrycases

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.covidappapi.model.CountryCases
import com.example.coviddata.CovidApp
import com.example.coviddata.R
import kotlinx.android.synthetic.main.fragment_cases_country.*

class CountryCasesFragment : Fragment() {
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_cases_country, container, false)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val countryName = requireArguments().getString("arg1")
        var showCountry: CountryCases? = null
        CovidApp.repository.countriesLiveData.observe(viewLifecycleOwner) { countriesData ->
            if (countriesData != null) {
                for (country in countriesData) {
                    if (country.name == countryName)
                        showCountry = country
                }
                country_textView.text = "${showCountry?.name}"
                total_number_textView.text = "${showCountry?.cases}"
                deaths_number_textView.text = "${showCountry?.deaths}"
                recovered_number_textView.text = "${showCountry?.recovered}"
                cases_per_one_million_number_textView.text = "${showCountry?.casesPerOneMillion}"
                deaths_per_one_million_number_textView.text = "${showCountry?.deathsPerOneMillion}"
                tests_per_one_million_number_textView.text = "${showCountry?.deathsPerOneMillion}"
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
    }
}
