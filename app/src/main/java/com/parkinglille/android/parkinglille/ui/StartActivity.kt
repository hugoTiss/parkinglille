package com.parkinglille.android.parkinglille.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import application.ParkingApplication
import com.parkinglille.android.parkinglille.R
import network.ParkingAvailabilityService
import javax.inject.Inject


abstract class StartActivity : AppCompatActivity() {

     @Inject
     lateinit var parkingAvailabilityService:ParkingAvailabilityService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
        (application as ParkingApplication).serviceComponent.inject(this)

        parkingAvailabilityService.availabilityParking("disponibilite-parkings",-1)

    }
}
