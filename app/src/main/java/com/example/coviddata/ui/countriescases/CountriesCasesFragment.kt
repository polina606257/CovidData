package com.example.coviddata.ui.countriescases

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.covidappapi.model.CountryCases
import com.example.coviddata.CovidApp
import com.example.coviddata.R
import kotlinx.android.synthetic.main.fragment_cases_countries.*

class CountriesCasesFragment : Fragment(), ListAdapterObserver {

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_cases_countries, container, false)
        CovidApp.repository.countriesLiveData.observe(viewLifecycleOwner) { covidCountryCases ->
            countriesRecyclerView.layoutManager = LinearLayoutManager(requireContext())
            countriesRecyclerView.adapter = ListAdapter(covidCountryCases, this)
        }

        getCountries()
        return root
    }


    fun getCountries() {
        CovidApp.repository.refreshCountriesCases()

    }

    override fun onItemClick(countryCases: CountryCases) {
        CovidApp.repository.refreshCountryCases(countryCases)

    }
}