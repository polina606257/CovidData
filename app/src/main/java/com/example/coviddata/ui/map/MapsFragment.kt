package com.example.coviddata.ui.map

import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory.defaultMarker
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.coviddata.CovidApp
import com.example.coviddata.R
import com.example.coviddata.model.CountryData
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions



class MapsFragment : Fragment() {

    val viewModel: MapViewModel by viewModels()

    init {
        CovidApp.repository.refreshAllCountriesData()
    }

    private val callback = OnMapReadyCallback { googleMap ->
        viewModel.allCountriesLastLiveData.observeForever { countries ->
            for (country in countries) {
                googleMap.addMarker(MarkerOptions().position(LatLng(country.countryInfo.lat, country.countryInfo.lng))
                        .title("Cases per 1 million: ${Math.round(country.casesPerOneMillion)}")
                        .icon(getPinColor(country)))
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }

    fun getPinColor(country: CountryData) : BitmapDescriptor {
        return when(viewModel.getGroupNumber(country)) {
            1 -> defaultMarker(BitmapDescriptorFactory.HUE_GREEN)
            2 -> defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)
            3 -> defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)
            4 -> defaultMarker(BitmapDescriptorFactory.HUE_ROSE)
            else ->
                defaultMarker(BitmapDescriptorFactory.HUE_RED)
        }
    }
}
