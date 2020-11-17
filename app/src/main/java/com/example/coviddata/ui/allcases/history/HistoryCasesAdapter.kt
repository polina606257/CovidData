package com.example.coviddata.ui.allcases.history

import android.text.SpannableStringBuilder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.bold
import androidx.core.text.color
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
            itemView.date_time_history.text = prepareTextForView("Data and time: ", item.datetime)
            itemView.all_cases_per_date.text = prepareTextForView("Cases: ", item.cases.toString())
            itemView.all_deaths_per_date.text = prepareTextForView("Deaths: ", item.deaths.toString())
            itemView.all_recovered_per_date.text = prepareTextForView("Recovered: ", item.recovered.toString())
        }
    }

    fun prepareTextForView(boldText: String, regularText: String) : SpannableStringBuilder {
        val s = SpannableStringBuilder().bold { append(boldText) }.append(regularText)
        return s
    }
}