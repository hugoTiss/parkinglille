package com.parkinglille.android.parkinglille.ui

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import application.ParkingApplication
import com.parkinglille.android.parkinglille.R



 class StartActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
        val intent = Intent(this,ParkingMapActivity::class.java)
        startActivity(intent)



    }
}
