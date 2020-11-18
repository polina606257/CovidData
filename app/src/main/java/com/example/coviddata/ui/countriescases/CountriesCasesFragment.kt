package com.example.coviddata.ui.countriescases

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coviddata.model.CountryData
import com.example.coviddata.R
import kotlinx.android.synthetic.main.fragment_cases_countries.*


class CountriesCasesFragment : Fragment(), CountriesCasesViewModel.Listener {

    val viewModel: CountriesCasesViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?)
            : View? {
        val root = inflater.inflate(R.layout.fragment_cases_countries, container, false)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        countriesRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        viewModel.setListener(this)
        viewModel.refreshCountries()
        viewModel.countriesLiveData.observe(viewLifecycleOwner){ countries->
            countries?.let{
                countriesRecyclerView.adapter = ListAdapter(it, viewModel)
            }
        }

        countriesOrderSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, selectedItem: View, position: Int, id: Long) {
                when (position) {
                    0 -> viewModel.sortParamLiveData.value = SortParam.NAME
                    1 -> viewModel.sortParamLiveData.value = SortParam.CASES
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
               return true
            }
            override fun onQueryTextChange(userInput: String?): Boolean {
                viewModel.filterParamLiveData.value = userInput ?: ""
                return true
            }
        })

    }

    override fun onShowCountryDetails(countryData: CountryData) {
        val action = CountriesCasesFragmentDirections
                .actionNavigationCountriesCasesToNavigationCountryCases(countryData.name)
        findNavController().navigate(action)
    }
}