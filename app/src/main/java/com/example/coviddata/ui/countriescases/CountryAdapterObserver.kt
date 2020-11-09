package com.example.coviddata.ui.countriescases

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.covidappapi.model.CountryCases
import com.example.coviddata.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.country_item.view.*

interface ListAdapterObserver {
    fun onItemClick(countryCases: CountryCases)
}

class ListAdapter(private val list: List<CountryCases>, val observer: ListAdapterObserver)
    : RecyclerView.Adapter<ListAdapter.CountryViewHolder>() {

    class CountryViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
            RecyclerView.ViewHolder(
                    inflater.inflate(R.layout.country_item, parent, false)) {
        var countryCases: CountryCases? = null
        fun bind(countryCases: CountryCases) {
            this.countryCases = countryCases
            itemView.country_name_textView.text = countryCases.name
            itemView.country_all_cases_textView.text = countryCases.cases.toString()
            Picasso.with(itemView.context)
                    .load(countryCases.countryInfo.flag)
                    .into(itemView.country_flag_imageView);
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val holder = CountryViewHolder(inflater, parent)
        holder.itemView.setOnClickListener {
            holder.countryCases?.let {
                observer.onItemClick(it)
            }
        }
        return holder
    }


    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val country = list[position]
        holder.bind(country)
    }

    override fun getItemCount(): Int = list.size }

