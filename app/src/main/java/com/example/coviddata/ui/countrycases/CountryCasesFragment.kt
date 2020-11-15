package com.example.coviddata.ui.countrycases

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.coviddata.R
import com.example.coviddata.databinding.FragmentCasesCountryBinding
import kotlinx.android.synthetic.main.fragment_cases_country.*

class CountryCasesFragment : Fragment() {

    val viewModel: CountryCasesViewModel by viewModels()
    val args: CountryCasesFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?)
            : View? {
        val root = inflater.inflate(R.layout.fragment_cases_country, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val countryName = args.countryName
        viewModel.initCountryName(countryName)
        val binding = FragmentCasesCountryBinding.bind(view)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

//        viewModel.countryLiveData.observe(viewLifecycleOwner) { country ->
//            if (country != null) {
//                country_textView.text = country.name
//                total_number_textView.text = country.cases.toString()
//                deaths_number_textView.text = country.deaths.toString()
//                recovered_number_textView.text = country.recovered.toString()
//                cases_per_one_million_number_textView.text = country.casesPerOneMillion.toString()
//                deaths_per_one_million_number_textView.text = country.deathsPerOneMillion.toString()
//                tests_per_one_million_number_textView.text = country.deathsPerOneMillion.toString()
//            } else {
//                country_textView.text = getText(R.string.no_data_text)
//                total_number_textView.text = getText(R.string.no_data_text)
//                deaths_number_textView.text = getText(R.string.no_data_text)
//                recovered_number_textView.text = getText(R.string.no_data_text)
//                cases_per_one_million_number_textView.text = getText(R.string.no_data_text)
//                deaths_per_one_million_number_textView.text = getText(R.string.no_data_text)
//                tests_per_one_million_number_textView.text = getText(R.string.no_data_text)
//            }
//        }
    }
}
