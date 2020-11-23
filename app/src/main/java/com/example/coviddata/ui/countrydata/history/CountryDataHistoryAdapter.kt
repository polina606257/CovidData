package com.example.coviddata.ui.countrydata.history

import android.text.SpannableStringBuilder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.bold
import androidx.recyclerview.widget.RecyclerView
import com.example.coviddata.R
import com.example.coviddata.model.CountryData
import kotlinx.android.synthetic.main.history_country_data_item.view.*
import kotlinx.android.synthetic.main.history_world_data_item.view.date_history

class ListAdapter(private val list: List<CountryData>)
    : RecyclerView.Adapter<ListAdapter.HistoryCasesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryCasesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.history_country_data_item, parent, false)
        val holder = HistoryCasesViewHolder(view)
        return holder
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: HistoryCasesViewHolder, position: Int) {
        val historyCases = list[position]
        holder.bind(historyCases)
    }

    class HistoryCasesViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        var historyCases: CountryData? = null

        fun bind(item: CountryData) {
            this.historyCases = item
            itemView.date_history.text = prepareTextForView("Data and time: ", item.date)
            val text: String = itemView.context.resources.getString(R.string.cases_textView)
            itemView.country_data_cases_per_date.text = prepareTextForView("Cases: ",
                item.cases.toString())
            itemView.country_data_deaths_per_date.text = prepareTextForView("Deaths: ",
                item.deaths.toString())
            itemView.country_data_recovered_per_date.text = prepareTextForView("Recovered: ",
                item.recovered.toString())
        }
        fun prepareTextForView(boldText: String, regularText: String) : SpannableStringBuilder {
            val s = SpannableStringBuilder().bold { append(boldText) }.append(regularText)
            return s
        }
    }
}