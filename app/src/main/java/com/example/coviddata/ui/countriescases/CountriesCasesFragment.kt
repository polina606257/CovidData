package com.example.coviddata.ui.countriescases

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.covidappapi.model.CountryCases
import com.example.coviddata.R
import kotlinx.android.synthetic.main.fragment_cases_countries.*


class CountriesCasesFragment : Fragment(), ListAdapterObserver {

    val viewModel: CountriesCasesViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?)
            : View? {
        val root = inflater.inflate(R.layout.fragment_cases_countries, container, false)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        countriesRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel.refreshCountries()
        viewModel.mediatorLiveData.observe(viewLifecycleOwner){ countries->
            countries?.let{
                countriesRecyclerView.adapter = ListAdapter(it, this)
            }
        }


//        viewModel.countryLiveData.observe(viewLifecycleOwner) { covidCountryCases ->
//            countriesRecyclerView.layoutManager = LinearLayoutManager(requireContext())
//            countriesRecyclerView.adapter = ListAdapter(covidCountryCases, this)
//        }


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
                viewModel.filterParamLiveData = ((userInput ?: "") as MutableLiveData<String>)
                return true
            }
        })

    }

    override fun onItemClick(countryCases: CountryCases) {
        val action = CountriesCasesFragmentDirections
                .actionNavigationCountriesCasesToNavigationCountryCases(countryCases.name)
        findNavController().navigate(action)
    }
}