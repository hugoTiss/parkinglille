package com.parkinglille.android.parkinglille.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.graphics.*
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomSheetBehavior
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.parkinglille.android.parkinglille.R
import com.parkinglille.android.parkinglille.ui.adapter.ParkingAdapter
import com.parkinglille.android.parkinglille.ui.viewmodel.ParkingViewModel
import data.RecordsItem
import kotlinx.android.synthetic.main.activity_parking_bottom_sheet.*
import kotlinx.android.synthetic.main.activity_parking_map.*
import android.widget.ImageView
import android.support.v4.content.ContextCompat
import android.graphics.drawable.Drawable
import android.support.annotation.DrawableRes
import com.google.android.gms.maps.model.BitmapDescriptor
import util.Utils.Companion.colorID
import util.Utils.Companion.tintImage


class ParkingMapActivity : AppCompatActivity(),OnMapReadyCallback,GoogleMap.OnMarkerClickListener{



    var parkingData:List<RecordsItem> = arrayListOf()
    lateinit var map:GoogleMap
    lateinit var parkingAdapter:ParkingAdapter
    lateinit var bottomSheetBehavior:BottomSheetBehavior<View>




    private val parkingViewModel by lazy { ViewModelProviders.of(this)[ParkingViewModel::class.java] }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parking_map)
        setSupportActionBar(activityParkingMapToolbar)
        initSearchView()
        val mMapFragment=supportFragmentManager.findFragmentById(R.id.activityParkingMapFragment) as SupportMapFragment
        mMapFragment.getMapAsync(this)
        parkingAdapter= ParkingAdapter(parkingData)
        activityParkingRecyclerView.apply {
            adapter=parkingAdapter
            layoutManager=LinearLayoutManager(this@ParkingMapActivity)
        }

        parkingViewModel.getParkingAvailability().observe(this, Observer {
           if(it!=null&&it.isNotEmpty()) {
               parkingData = it
               parkingAdapter.addListParking(parkingData)
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
        if(bottomSheetBehavior.state==BottomSheetBehavior.STATE_COLLAPSED)bottomSheetBehavior.state=BottomSheetBehavior.STATE_EXPANDED
        parkingAdapter.addListParking(parkingData.filter { it.keys==p0?.tag.toString() })
        return false
    }

    private fun initSearchView(){
        bottomSheetBehavior= BottomSheetBehavior.from(activityParkingBottomView)
        activityParkingSearchView.setOnQueryTextListener(object :android.support.v7.widget.SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if(!newText.isNullOrEmpty()) {
                   parkingAdapter.addListParking( parkingData.filter { it.fields.libelle.contains(newText!!, true) })
                }
                else
                {
                    parkingAdapter.addListParking(parkingData)
                }
                return true
            }
        })
        activityParkingSearchView.setOnClickListener(object :View.OnClickListener{
            override fun onClick(p0: View?) {
                if(bottomSheetBehavior.state==BottomSheetBehavior.STATE_COLLAPSED)bottomSheetBehavior.state=BottomSheetBehavior.STATE_EXPANDED
            }
        })
    }


    private fun addMarkerMap(parkingData:List<RecordsItem>){
        var i = 0
            for(parking in parkingData){
                i++
                Log.d("ParkingMapActivity","$i  ${parking.fields.coordgeo[0]} ${parking.fields.coordgeo[1]}")

                val bitmap = BitmapFactory.decodeResource(resources,R.mipmap.ic_radiobox_marked_grey600_24dp)
                val icon = BitmapDescriptorFactory.fromBitmap(tintImage(bitmap,resources.getColor(colorID(parking.fields.dispo.toFloat(),parking.fields.max.toFloat()),null)))

                val marker= map.addMarker(MarkerOptions().position(LatLng(parking.fields.coordgeo[0],parking.fields.coordgeo[1]))
                        .title(parking.fields.libelle)
                        .icon(icon)
                )
                marker?.tag=parking.fields.id
            }
    }





}
