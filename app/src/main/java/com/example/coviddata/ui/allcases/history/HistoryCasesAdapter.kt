package com.example.coviddata.ui.allcases.history

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.covidappapi.model.AllCases
import com.example.coviddata.R
import kotlinx.android.synthetic.main.history_cases_item.view.*

class ListAdapter(private val list: List<AllCases>)
    : RecyclerView.Adapter<ListAdapter.HistoryCasesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryCasesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.history_cases_item, parent, false)
        val holder = HistoryCasesViewHolder(view)
        return holder
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: HistoryCasesViewHolder, position: Int) {
        val historyCases = list[position]
        holder.bind(historyCases)
    }

    inner class HistoryCasesViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        var historyCases: AllCases? = null

        fun bind(item: AllCases) {
            this.historyCases = item
            itemView.date_time_history.text = "Date and time:   ${item.datetime}"
            itemView.all_cases_per_date_time.text = "Cases:  ${item.cases}"
            itemView.all_deaths_per_date_time.text = "Deaths:  ${item.deaths}"
            itemView.all_recovered_per_date_time.text = "Recovered:  ${item.recovered}"
        }
    }
}