package com.example.coviddata.ui.allcountriesdata

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.coviddata.model.CountryData
import com.example.coviddata.databinding.CountryItemBinding

class ListAdapter(private val list: List<CountryData>, private val viewModelAll: AllCountriesDataViewModel)
    : RecyclerView.Adapter<ListAdapter.CountryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CountryItemBinding.inflate(inflater, parent, false)
        val holder = CountryViewHolder(binding, viewModelAll)
        return holder
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val country = list[position]
        holder.bind(country)
    }

    class CountryViewHolder(
            val binding: CountryItemBinding,
            val viewModelAll: AllCountriesDataViewModel
    ): RecyclerView.ViewHolder(binding.root) {
        var countryData: CountryData? = null

        fun bind(item: CountryData) {
            this.countryData = item
            binding.countryData = item
            binding.viewModel = viewModelAll
            binding.executePendingBindings()
        }
    }
}



