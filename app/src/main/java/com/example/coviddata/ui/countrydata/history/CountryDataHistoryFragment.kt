package com.example.coviddata.ui.countrydata.history

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coviddata.R
import com.example.coviddata.ui.countrydata.CountryDataFragmentArgs
import com.example.coviddata.ui.countrydata.CountryDataViewModel
import kotlinx.android.synthetic.main.fragment_country_data_history.*

class CountryDataHistoryFragment : Fragment() {

    val viewModel: CountryDataViewModel by viewModels()
    val args: CountryDataFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_country_data_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val countryName = args.countryName
        viewModel.initCountryName(countryName)
        historyCountryDataRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel.countryDataHistoryLiveData.observe(viewLifecycleOwner) { historyData ->
            historyData?.let {
                historyCountryDataRecyclerView.adapter = ListAdapter(it)
                Log.d("countryData", historyData.toString())
            }
        }
    }
}