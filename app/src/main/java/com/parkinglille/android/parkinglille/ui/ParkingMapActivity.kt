package com.parkinglille.android.parkinglille.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapFragment
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.parkinglille.android.parkinglille.R
import com.parkinglille.android.parkinglille.ui.viewmodel.ParkingViewModel
import data.RecordsItem

class ParkingMapActivity : AppCompatActivity(),OnMapReadyCallback {

    var mMapFragment:MapFragment?=null
    var parkingData:List<RecordsItem> = arrayListOf()
    var map:GoogleMap?=null



    private val parkingViewModel by lazy { ViewModelProviders.of(this)[ParkingViewModel::class.java] }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parking_map)

        mMapFragment=MapFragment.newInstance()

        fragmentManager.beginTransaction().add(R.id.activityParkingMapFragment,mMapFragment).commit()
        mMapFragment?.getMapAsync(this)

        parkingViewModel.getParkingAvailability().observe(this, Observer {
           if(it!=null&&it.size>0) {
               parkingData = it
               if (map != null) addMarkerMap(parkingData)
           }
        })

    }
    override fun onMapReady(p0: GoogleMap?) {
        map=p0
        if (!parkingData.isEmpty())addMarkerMap(parkingData)
    }

    private fun addMarkerMap(parkingData:List<RecordsItem>){
            for(parking in parkingData){
                Log.d("ParkingMapActivity","${parking.fields.coordgeo[0]} ${parking.fields.coordgeo[1]}")
                map?.addMarker(MarkerOptions().position(LatLng(parking.fields.coordgeo[0],parking.fields.coordgeo[1])).title(parking.fields.libelle))
            }
    }


}
