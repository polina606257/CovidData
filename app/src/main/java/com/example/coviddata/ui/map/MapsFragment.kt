package com.example.coviddata.ui.map

import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.coviddata.R
import com.example.coviddata.model.CountryData
import com.example.coviddata.ui.EventObserver
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.fragment_map.*


class MapsFragment : Fragment() {

    val viewModel: MapViewModel by viewModels()

    private val callback = OnMapReadyCallback { googleMap ->
        viewModel.countriesLiveData.observeForever { countries ->
            countries?.let {
                for (country in countries) {
                    val markerInfo = viewModel.getMarkerInfo(country)
                    googleMap.addMarker(MarkerOptions()
                        .position(LatLng(country.countryInfo.lat, country.countryInfo.lng))
                            .title("${getString(markerInfo.markerTitle)} " +
                                    "${markerInfo.markerNumber}")
                            .icon(getMarker(country)))
                }
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_map, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
        viewModel.popupMessage.observe(viewLifecycleOwner, EventObserver {
            Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
        })

        mapSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, selectedItem: View, position: Int, id: Long) {
                when (position) {
                    0 -> viewModel.sortParamLiveData.value = SortParamMap.CASES
                    1 -> viewModel.sortParamLiveData.value = SortParamMap.DEATHS
                    2 -> viewModel.sortParamLiveData.value = SortParamMap.RECOVERED
                    3 -> viewModel.sortParamLiveData.value = SortParamMap.CASES_PER_MILLION
                    4 -> viewModel.sortParamLiveData.value = SortParamMap.DEATHS_PER_MILLION
                    5 -> viewModel.sortParamLiveData.value = SortParamMap.TEST_PER_MILLION
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }
    }

    private fun getMarker(country: CountryData): BitmapDescriptor? {
        val resId = viewModel.getMarkerInfo(country).markerId
        return ContextCompat.getDrawable(requireContext(), resId)?.run {
            setBounds(0, 0, intrinsicWidth, intrinsicHeight)
            val bitmap =
                Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, Bitmap.Config.ARGB_8888)
            draw(Canvas(bitmap))
            BitmapDescriptorFactory.fromBitmap(bitmap)
        }
    }
}
