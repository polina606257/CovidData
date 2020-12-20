package com.example.coviddata.ui.countrydata.history

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coviddata.CovidApp
import com.example.coviddata.R
import com.example.coviddata.ui.countrydata.CountryDataFragmentArgs
import com.example.coviddata.ui.countrydata.CountryDataViewModel
import com.example.coviddata.ui.countrydata.CountryViewModelFactory
import com.example.coviddata.ui.worlddata.WorldViewModelFactory
import kotlinx.android.synthetic.main.fragment_country_data_history.*
import javax.inject.Inject

class CountryDataHistoryFragment : Fragment() {

    @Inject lateinit var viewModelFactory: CountryViewModelFactory
    val viewModel: CountryDataViewModel by viewModels{viewModelFactory}
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

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as CovidApp).appComponent.inject(this)
    }
}