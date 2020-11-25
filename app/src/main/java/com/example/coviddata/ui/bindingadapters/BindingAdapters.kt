package com.example.coviddata.ui.bindingadapters

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

@BindingAdapter(value = ["app:imageURL"], requireAll = false)
fun ImageView.imageURL(url: String){
    try {
        Picasso.with(context)
                .load(url)
                .into(this)
    }catch (e: Exception){

    }
}

@BindingAdapter(value = ["app:visibilityViewLoading"], requireAll = false)
fun View.visibilityViewLoading(isLoad: Any?) {
    visibility = if(isLoad == true)
        View.VISIBLE
    else
        View.INVISIBLE
}

@BindingAdapter(value = ["app:visibilityViewNotLoading"], requireAll = false)
fun View.visibilityViewNotLoading(notLoad: Any?) {
    visibility = if(notLoad == false)
        View.VISIBLE
    else
        View.INVISIBLE
}