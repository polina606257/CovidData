package com.example.coviddata.ui.countriescases

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.map
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.covidappapi.model.CountryCases
import com.example.coviddata.R
import kotlinx.android.synthetic.main.fragment_cases_countries.*


class CountriesCasesFragment : Fragment(), ListAdapterObserver {

    val viewModel: CountriesCasesViewModel by viewModels()
    var sortParam: SortParam? = null
    lateinit var adapter: ListAdapter

    enum class SortParam {
        NAME, CASES
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?)
            : View? {
        val root = inflater.inflate(R.layout.fragment_cases_countries, container, false)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.sortParam = sortParam
        var layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(requireContext())

        val binding = FragmentCasesCountriesBinding.bind(view)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        adapter = ListAdapter(viewModel.countryLiveData, this)
        binding.countriesRecyclerView.adapter(adapter)

//        viewModel.countryLiveData.observe(viewLifecycleOwner) { covidCountryCases ->
//            countriesRecyclerView.layoutManager = LinearLayoutManager(requireContext())
//            countriesRecyclerView.adapter = ListAdapter(covidCountryCases, this)
//        }


        countriesOrderSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, selectedItem: View, position: Int, id: Long) {
                when (position) {
                    0 -> sortParam = SortParam.NAME
                    1 -> sortParam = SortParam.CASES
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
               return false
            }
            override fun onQueryTextChange(p0: String?): Boolean {
                adapter.filter?.filter(p0)
                return false
            }
        })

    }

    override fun onItemClick(countryCases: CountryCases) {
        val action = CountriesCasesFragmentDirections
                .actionNavigationCountriesCasesToNavigationCountryCases(countryCases.name)
        findNavController().navigate(action)
    }
}