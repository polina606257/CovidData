package com.example.coviddata.ui.map

import android.graphics.Bitmap
import android.graphics.Canvas
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory.defaultMarker
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.example.coviddata.R
import com.example.coviddata.model.CountryData
import com.example.coviddata.ui.EventObserver
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions



class MapsFragment : Fragment() {

    val viewModel: MapViewModel by viewModels()

    private val callback = OnMapReadyCallback { googleMap ->
        viewModel.allCountriesLastDataLiveData.observeForever { countries ->
            for (country in countries) {
                googleMap.addMarker(MarkerOptions().position(LatLng(country.countryInfo.lat, country.countryInfo.lng))
                        .title("Cases per 1 million: ${Math.round(country.casesPerOneMillion)}")
                        .icon(getPinColor(country)))
            }
//            viewModel._refreshWorldDataLiveData.value = false
//            viewModel.setDownloadStatus(false)
        }
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_map, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
//        viewModel.setDownloadStatus(true)
//        CovidApp.repository.refreshAllCountriesData()
        mapFragment?.getMapAsync(callback)
        viewModel.popupMessage.observe(viewLifecycleOwner, EventObserver{
            Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
        })
    }

//    fun getPinColor(country: CountryData) : BitmapDescriptor {
//        return when(viewModel.getGroupNumber(country)) {
//            1 -> defaultMarker(BitmapDescriptorFactory.HUE_GREEN)
//            2 -> defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)
//            3 -> defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)
//            4 -> defaultMarker(BitmapDescriptorFactory.HUE_ROSE)
//            else ->
//                defaultMarker(BitmapDescriptorFactory.HUE_RED)
//        }
//    }


    fun getPinColor(country: CountryData) : BitmapDescriptor? {
        return when(viewModel.getGroupNumber(country)) {
            1 -> bitmapDescriptorFromVector(R.drawable.group1mapmarker)
            2 -> bitmapDescriptorFromVector(R.drawable.group2mapmarker)
            3 -> bitmapDescriptorFromVector(R.drawable.group3mapmarker)
            4 -> bitmapDescriptorFromVector(R.drawable.group4mapmarker)
            else ->
                bitmapDescriptorFromVector(R.drawable.group5mapmarker)
        }
    }

    private fun bitmapDescriptorFromVector(vectorResId: Int): BitmapDescriptor? {
        return ContextCompat.getDrawable(requireContext(), vectorResId)?.run {
            setBounds(0, 0, intrinsicWidth, intrinsicHeight)
            val bitmap =
                    Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, Bitmap.Config.ARGB_8888)
            draw(Canvas(bitmap))
            BitmapDescriptorFactory.fromBitmap(bitmap)
        }
    }
}
