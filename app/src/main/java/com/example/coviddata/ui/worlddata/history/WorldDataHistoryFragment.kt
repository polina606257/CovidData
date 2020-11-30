package com.example.coviddata.ui.worlddata.history

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coviddata.R
import com.example.coviddata.model.WorldData
import com.example.coviddata.ui.worlddata.WorldDataViewModel
import kotlinx.android.synthetic.main.fragment_history_world_data.*

class WorldDataHistoryFragment : Fragment() {

    val viewModel: WorldDataViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?)
            : View? {
        val root = inflater.inflate(R.layout.fragment_history_world_data, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        historyWorldDataRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        viewModel.worldDataHistoryLiveData.observe(viewLifecycleOwner) { historyCases ->
            historyCases?.let {historyWorldDataRecyclerView.adapter = ListAdapter(it as List<WorldData>) }
        }
    }
}