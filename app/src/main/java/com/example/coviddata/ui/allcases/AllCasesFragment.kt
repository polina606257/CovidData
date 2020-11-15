package com.example.coviddata.ui.allcases

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.coviddata.R
import com.example.coviddata.databinding.FragmentCasesAllBinding

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
    }
}
