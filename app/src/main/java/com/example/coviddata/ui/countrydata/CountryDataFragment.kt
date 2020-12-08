package com.example.coviddata.ui.countrydata

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.coviddata.R
import com.example.coviddata.databinding.FragmentDataCountryBinding
import com.example.coviddata.ui.EventObserver
import kotlinx.android.synthetic.main.fragment_data_country.*

class CountryDataFragment : Fragment() {

    val viewModel: CountryDataViewModel by viewModels()
    val args: CountryDataFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_data_country, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val countryName = args.countryName
        viewModel.initCountryName(countryName)
        val binding = FragmentDataCountryBinding.bind(view)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        historyCountryDataButton.setOnClickListener { button ->
            val action = CountryDataFragmentDirections.actionNavigationCountryCasesToCountryDataHistoryFragment(countryName)
            findNavController().navigate(action)
        }

        viewModel.popupMessage.observe(viewLifecycleOwner, EventObserver{
            Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
        })
    }
}
