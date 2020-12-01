package com.example.coviddata.ui.allcountriesdata

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coviddata.model.CountryData
import com.example.coviddata.R
import com.example.coviddata.ui.EventObserver
import kotlinx.android.synthetic.main.fragment_data_all_countries.*

class AllCountriesDataFragment : Fragment(), AllCountriesDataViewModel.Listener {

    val viewModelAll: AllCountriesDataViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?)
            : View? {
        val root = inflater.inflate(R.layout.fragment_data_all_countries, container, false)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        countriesRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        viewModelAll.setListener(this)
        viewModelAll.refreshCountriesData()
//        viewModelAll.refreshCountries()
        viewModelAll.countriesLiveData.observe(viewLifecycleOwner){ countries->
            countries?.let{
                countriesRecyclerView.adapter = ListAdapter(it, viewModelAll)
            }
            viewModelAll.popupMessage.observe(viewLifecycleOwner, EventObserver{
                Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
            })
        }

        countriesSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, selectedItem: View, position: Int, id: Long) {
                when (position) {
                    0 -> viewModelAll.sortParamLiveData.value = SortParam.NAME
                    1 -> viewModelAll.sortParamLiveData.value = SortParam.CASES
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }

        countriesSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
               return true
            }
            override fun onQueryTextChange(userInput: String?): Boolean {
                viewModelAll.filterParamLiveData.value = userInput ?: ""
                return true
            }
        })

    }

    override fun onShowCountryDetails(countryData: CountryData) {
        val action = AllCountriesDataFragmentDirections
                .actionNavigationCountriesCasesToNavigationCountryCases(countryData.name)
        findNavController().navigate(action)
    }
}