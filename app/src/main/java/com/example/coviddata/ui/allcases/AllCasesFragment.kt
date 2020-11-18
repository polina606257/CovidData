package com.example.coviddata.ui.allcases

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.coviddata.R
import com.example.coviddata.databinding.FragmentCasesAllBinding
import com.example.coviddata.ui.countriescases.CountriesCasesFragmentDirections
import kotlinx.android.synthetic.main.fragment_cases_all.*

class AllCasesFragment : Fragment() {

    val viewModel: AllCasesViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?)
            : View? {
        val root = inflater.inflate(R.layout.fragment_cases_all, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentCasesAllBinding.bind(view)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        historyCasesButton.setOnClickListener {button ->
            val action = AllCasesFragmentDirections.actionNavigationAllCasesToAllCasesHistoryFragment2()
            findNavController().navigate(action)
        }
    }
}
