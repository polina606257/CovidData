package com.example.coviddata.ui.countrydata.history

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coviddata.R
import com.example.coviddata.model.CountryData
import com.example.coviddata.model.CountryInfo
import com.example.coviddata.ui.worlddata.history.ListAdapter
import kotlinx.android.synthetic.main.fragment_country_data_history.*
import java.time.LocalDate

class CountryDataHistoryFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_country_data_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val listCountryData = listOf<CountryData>(CountryData(LocalDate.now().toString(), "USA",
                CountryInfo(" "), 300, 20, 10, 3.00,
                3.00, 3.00), CountryData(LocalDate.now().toString(), "Russia",
                CountryInfo(" "), 200, 10, 10, 2.00,
                2.00, 2.00 ))
        historyCountryDataRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        historyCountryDataRecyclerView.adapter = ListAdapter(listCountryData)

//        viewModel.worldDataHistoryLiveData.observe(viewLifecycleOwner) { historyCases ->
//            historyCases?.let {historyWorldDataRecyclerView.adapter = ListAdapter(it) }
//        }
    }
}