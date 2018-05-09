package com.parkinglille.android.parkinglille.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.parkinglille.android.parkinglille.R
import com.parkinglille.android.parkinglille.ui.viewmodel.ParkingViewModel
import kotlinx.android.synthetic.main.activity_parking_map.*

class ParkingMapActivity : AppCompatActivity() {

    private val parkingViewModel by lazy { ViewModelProviders.of(this)[ParkingViewModel::class.java] }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parking_map)


        parkingViewModel.getParkingAvailability().observe(this, Observer {
            if(it!=null&&it.size>0)
                it.get(0)?.fields?.adresse.run {
                activityParkingMapTextView.setText(this)
                }

        })

    }
}
