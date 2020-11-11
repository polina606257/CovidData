package com.example.coviddata.ui.allcases

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_cases_all.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.coviddata.CovidApp
import com.example.coviddata.R
import kotlinx.android.synthetic.main.fragment_cases_all.*

class AllCasesFragment : Fragment() {
    //val viewModel = AllCasesViewModel()
    val viewModel: AllCasesViewModel by viewModels()
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_cases_all, container, false)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /*viewModel.allCasesLiveData.observe(viewLifecycleOwner) { covidData ->
            if (covidData != null) {
                casesTextView.text = "Cases: ${covidData.cases}"
                deathsTextView.text = "Deaths: ${covidData.deaths}"
                recoveredTextView.text = "Recovered: ${covidData.recovered}"
            } else{
                casesTextView.text = "Cases: no data"
                deathsTextView.text = "Deaths: no data"
                recoveredTextView.text = "Recovered: no data"
            }
        }*/
//        findAllCasesBtn.setOnClickListener{
//            CovidApp.repository.refreshAllCases()
//        }
        refreshButton.setOnClickListener {
            viewModel.refreshAllCases()
        }
    }
}
