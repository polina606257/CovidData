package com.example.coviddata.ui.allcases.history

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.covidappapi.model.AllCases
import com.example.coviddata.R
import com.example.coviddata.ui.countriescases.ListAdapter
import kotlinx.android.synthetic.main.fragment_all_cases_history.*
import kotlinx.android.synthetic.main.fragment_cases_countries.*
import java.time.LocalDateTime

class AllCasesHistoryFragment : Fragment() {



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?)
            : View? {
        val root = inflater.inflate(R.layout.fragment_all_cases_history, container, false)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val list: List<AllCases> = listOf(AllCases(200, 30, 15, LocalDateTime.now().toString()),
        AllCases(300, 30, 40, LocalDateTime.now().toString()))

                historyCasesRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        historyCasesRecyclerView.adapter = ListAdapter(list)
    }
}