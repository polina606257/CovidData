package com.example.coviddata.ui.countriescases

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.covidappapi.model.CountryCases
import com.example.coviddata.databinding.CountryItemBinding
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.country_item.view.*

interface ListAdapterObserver {
    fun onItemClick(countryCases: CountryCases)
}

class ListAdapter(private val list: List<CountriesCasesViewModel>, val observer: ListAdapterObserver)
    : RecyclerView.Adapter<ListAdapter.CountryViewHolder>(), Filterable {
    var valueFilter: ValueFilter? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CountryItemBinding.inflate(inflater)
        val holder = CountryViewHolder(binding)

        holder.itemView.setOnClickListener {
            holder.countryCases?.let {
                observer.onItemClick(it)
            }
        }
        return holder
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val country = list[position]
        holder.bind(country)
    }

    inner class CountryViewHolder(val binding: CountryItemBinding) : RecyclerView.ViewHolder(binding.root) {
        var countryCases: CountryCases? = null

        fun bind(item: CountriesCasesViewModel) {
//            this.countryCases = item
                binding.modelView = item
                binding.executePendingBindings()
//                itemView.country_name_textView.text = item.name
//                itemView.country_all_cases_textView.text = item.cases.toString()
//                Picasso.with(itemView.context)
//                        .load(item.countryInfo.flag)
//                        .into(itemView.country_flag_imageView);
        }
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getFilter(): Filter? {
        if(valueFilter == null)
            valueFilter = ValueFilter(list)
        return valueFilter
    }
}



