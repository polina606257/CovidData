package com.example.coviddata.ui.worlddata

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.coviddata.R
import com.example.coviddata.databinding.FragmentDataWorldBinding
import com.example.coviddata.ui.EventObserver
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_data_world.*

@AndroidEntryPoint
class WorldDataFragment : Fragment() {

    val viewModel: WorldDataViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?)
            : View? {
        val root = inflater.inflate(R.layout.fragment_data_world, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentDataWorldBinding.bind(view)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        historyWorldDataButton.setOnClickListener { button ->
            val action = WorldDataFragmentDirections.actionNavigationAllCasesToAllCasesHistoryFragment2()
            findNavController().navigate(action)
        }
        viewModel.popupMessage.observe(viewLifecycleOwner, EventObserver{
            Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
        })
    }
}
