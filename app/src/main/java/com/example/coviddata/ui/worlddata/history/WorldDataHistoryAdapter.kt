package com.example.coviddata.ui.worlddata.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.coviddata.model.WorldData
import com.example.coviddata.databinding.HistoryWorldDataItemBinding
import kotlinx.android.synthetic.main.history_world_data_item.view.*

class ListAdapter(private val list: List<WorldData>)
    : RecyclerView.Adapter<ListAdapter.HistoryWorldDataViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryWorldDataViewHolder {
        val inflator = LayoutInflater.from(parent.context)
        var binding = HistoryWorldDataItemBinding.inflate(inflator, parent, false)
        val holder = HistoryWorldDataViewHolder(binding)
        return holder
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: HistoryWorldDataViewHolder, position: Int) {
        val historyWorldData = list[position]
        holder.bind(historyWorldData)
    }

    class HistoryWorldDataViewHolder(val binding: HistoryWorldDataItemBinding) : RecyclerView.ViewHolder(binding.root) {
        var historyWorldData: WorldData? = null

        fun bind(item: WorldData) {
            this.historyWorldData = item
            binding.worldData = item
            binding.executePendingBindings()
        }
    }
}