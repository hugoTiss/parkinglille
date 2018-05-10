package com.parkinglille.android.parkinglille.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.parkinglille.android.parkinglille.R
import com.parkinglille.android.parkinglille.ui.viewmodel.ParkingViewModel
import data.RecordsItem

class ParkingMapActivity : AppCompatActivity(),OnMapReadyCallback,GoogleMap.OnMarkerClickListener{



    var parkingData:List<RecordsItem> = arrayListOf()
    lateinit var map:GoogleMap



    private val parkingViewModel by lazy { ViewModelProviders.of(this)[ParkingViewModel::class.java] }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parking_map)
        val mMapFragment=supportFragmentManager.findFragmentById(R.id.activityParkingMapFragment) as SupportMapFragment

        mMapFragment.getMapAsync(this)


        parkingViewModel.getParkingAvailability().observe(this, Observer {
           if(it!=null&&it.isNotEmpty()) {
               parkingData = it
               if (::map.isInitialized) addMarkerMap(parkingData)
           }
        })

    }
    override fun onMapReady(p0: GoogleMap) {
        map=p0
        map.setOnMarkerClickListener(this)
        if (!parkingData.isEmpty()) {
            addMarkerMap(parkingData)

        }
    }

    override fun onMarkerClick(p0: Marker?): Boolean {
        Log.d("ParkingMapActivity","Click")
        return false
    }


    private fun addMarkerMap(parkingData:List<RecordsItem>){
        var i = 0
            for(parking in parkingData){
                i++
                Log.d("ParkingMapActivity","$i  ${parking.fields.coordgeo[0]} ${parking.fields.coordgeo[1]}")
                val marker= map.addMarker(MarkerOptions().position(LatLng(parking.fields.coordgeo[0],parking.fields.coordgeo[1])).title(parking.fields.libelle))
                marker?.tag=parking.fields.id

            }

    }




}
