package com.example.coviddata.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.covidappapi.model.CountryCases
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.country_item.view.*

@BindingAdapter("app:flag")
fun countryFlag(view: ImageView, flag: String) {
    Picasso.with(view.context)
            .load(flag)
            .into(view.country_flag_imageView);
}