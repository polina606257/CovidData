package com.example.coviddata.ui.bindingadapters

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import com.example.coviddata.model.WorldData
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.country_item.view.*

@BindingAdapter(value = ["app:imageURL"], requireAll = false)
fun ImageView.imageURL(url: String){
    try {
        Picasso.with(context)
                .load(url)
                .into(this)
    }catch (e: Exception){

    }
}

@BindingAdapter(value = ["app:visibilityView"], requireAll = false)
fun View.visibilityView(value: Any?) {
    visibility = if(value != null)
        View.VISIBLE
    else
        View.INVISIBLE
}