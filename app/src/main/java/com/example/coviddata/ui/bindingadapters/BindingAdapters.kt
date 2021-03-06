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

@BindingAdapter(value = ["app:visibleIfTrue"], requireAll = false)
fun View.visibleIfTrue(value: Boolean) {
    visibility = if(value)
        View.VISIBLE
    else
        View.INVISIBLE
}

//@BindingAdapter(value = ["app:visibleIfFalse"], requireAll = false)
//fun View.visibleIfFalse(value: Boolean) {
//    visibility = if(!value)
//        View.VISIBLE
//    else
//        View.INVISIBLE
//}

@BindingAdapter(value = ["app:visibleIfNotNull"], requireAll = false)
fun View.visibleIfNotNull(value: Any?) {
    visibility = if(value != null)
        View.VISIBLE
    else
        View.INVISIBLE
}