package com.example.coviddata.ui.countriescases

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.coviddata.model.CountryData
import com.example.coviddata.R
import com.example.coviddata.databinding.CountryItemBinding
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.country_item.view.*

class ListAdapter(private val list: List<CountryData>, private val viewModel: CountriesCasesViewModel)
    : RecyclerView.Adapter<ListAdapter.CountryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CountryItemBinding.inflate(inflater, parent, false)
        val holder = CountryViewHolder(binding, viewModel)
        return holder
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val country = list[position]
        holder.bind(country)
    }

    class CountryViewHolder(
            val binding: CountryItemBinding,
            val viewModel: CountriesCasesViewModel
    ): RecyclerView.ViewHolder(binding.root) {
        var countryData: CountryData? = null

        fun bind(item: CountryData) {
            this.countryData = item
            binding.countryData = item
            binding.viewModel = viewModel
            binding.executePendingBindings()
        }
    }
}



