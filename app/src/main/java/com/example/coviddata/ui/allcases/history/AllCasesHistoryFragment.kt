package com.example.coviddata.ui.allcases.history

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coviddata.R
import com.example.coviddata.ui.allcases.AllCasesViewModel
import kotlinx.android.synthetic.main.fragment_all_cases_history.*

class AllCasesHistoryFragment : Fragment() {

    val viewModel: AllCasesViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?)
            : View? {
        val root = inflater.inflate(R.layout.fragment_all_cases_history, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        historyCasesRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        viewModel.worldDataHistoryLiveData.observe(viewLifecycleOwner) { historyCases ->
            historyCases?.let {historyCasesRecyclerView.adapter = ListAdapter(it) }
        }
    }
}