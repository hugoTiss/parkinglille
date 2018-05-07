package com.parkinglille.android.parkinglille.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import application.ParkingApplication
import com.parkinglille.android.parkinglille.R
import dagger.DaggerServiceComponent
import data.ResponseParking
import network.ParkingAvailabilityService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


 class StartActivity : AppCompatActivity() {

     @Inject
     lateinit var parkingAvailabilityService:ParkingAvailabilityService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
        DaggerServiceComponent.create().inject(this)



        var callParkingService = parkingAvailabilityService.availabilityParking("disponibilite-parkings",-1)
        callParkingService.enqueue(object : Callback<ResponseParking>{
            override fun onFailure(call: Call<ResponseParking>?, t: Throwable?) {
                Log.e("SERVICE","RESPONSE FAILURE")
            }

            override fun onResponse(call: Call<ResponseParking>?, response: Response<ResponseParking>?) {
                Log.d("SERVICE","RESPONSE SUCCESS")
                val response =response?.body()?.records?.get(0)?.fields?.adresse
                if(response!=null)Toast.makeText(this@StartActivity,response,Toast.LENGTH_SHORT).show()
            }


        })

    }
}
