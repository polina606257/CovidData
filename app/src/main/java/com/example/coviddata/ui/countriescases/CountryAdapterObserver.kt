package com.example.coviddata.ui.countriescases

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
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
    //var valueFilter: ValueFilter? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.country_item, parent, false)
        val holder = CountryViewHolder(view)

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

    inner class CountryViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        var countryCases: CountryCases? = null

        fun bind(item: CountryCases) {
            this.countryCases = item
            itemView.country_name_textView.text = item.name
            itemView.country_all_cases_textView.text = item.cases.toString()
            try {
                Picasso.with(itemView.context)
                        .load(item.countryInfo.flag)
                        .into(itemView.country_flag_imageView)
            }catch (e: Exception){

            }

        }
    }
}



