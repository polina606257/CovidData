package com.example.coviddata.ui.allcountriesdata

import android.content.Context
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
import com.example.coviddata.CovidApp
import com.example.coviddata.R
import com.example.coviddata.databinding.FragmentDataAllCountriesBinding
import com.example.coviddata.ui.EventObserver
import com.example.coviddata.ui.worlddata.WorldViewModelFactory
import kotlinx.android.synthetic.main.fragment_data_all_countries.*
import javax.inject.Inject

class AllCountriesDataFragment : Fragment() {

    @Inject lateinit var viewModelFactory: AllCountriesViewModelFactory
    val viewModel: AllCountriesDataViewModel by viewModels{viewModelFactory}

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?)
            : View? {
        val root = inflater.inflate(R.layout.fragment_data_all_countries, container, false)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentDataAllCountriesBinding.bind(view)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        countriesRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        viewModel.countriesLiveData.observe(viewLifecycleOwner){ countries->
            countries?.let{
                countriesRecyclerView.adapter = ListAdapter(it, viewModel)
            }
        }
        viewModel.popupMessage.observe(viewLifecycleOwner, EventObserver{
            Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
        })

        countriesSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, selectedItem: View, position: Int, id: Long) {
                when (position) {
                    0 -> viewModel.sortParamCountriesLiveData.value = SortParamCountries.NAME
                    1 -> viewModel.sortParamCountriesLiveData.value = SortParamCountries.CASES
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
                viewModel.filterParamLiveData.value = userInput ?: ""
                return true
            }
        })

        viewModel.navigateToDetails.observe(viewLifecycleOwner, EventObserver{
            val action = AllCountriesDataFragmentDirections
                .actionNavigationCountriesCasesToNavigationCountryCases(it.name)
        findNavController().navigate(action) })
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as CovidApp).appComponent.inject(this)
    }
}