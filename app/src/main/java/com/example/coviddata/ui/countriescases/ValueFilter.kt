package com.example.coviddata.ui.countriescases

import android.widget.Filter

/*
class ValueFilter(list: List<CountriesCasesViewModel>) : Filter() {

    val countriesNameList = list.map { countryCases -> countryCases.name }
    var mData:List<CountriesCasesViewModel>? = null
    var mFilterList: List<CountriesCasesViewModel>? = null

    init {
        mData = countriesNameList
        mFilterList = countriesNameList
    }

    override fun performFiltering(constraint: CharSequence?): Filter.FilterResults {
        val results = Filter.FilterResults()
        if(constraint != null && constraint.length > 0) {
            val filterList: ArrayList<CountriesCasesViewModel> = ArrayList<CountriesCasesViewModel>()
            for (i in 0..mFilterList.size)
                if (mFilterList.get(i).toUpperCase().contains(constraint.toString().toUpperCase())) {
                    filterList.add(mFilterList.get(i))
                }
            results.count = filterList.size
            results.values = filterList
        }
        else {
            results.count = mFilterList.size
            results.values = mFilterList
        }
        return results
    }

    override fun publishResults(constraint: CharSequence?, results: Filter.FilterResults?) {
        mData = results?.values
        notifyDataSetChanged()
    }
}
*/
