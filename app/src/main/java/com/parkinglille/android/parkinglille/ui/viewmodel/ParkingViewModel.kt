package com.parkinglille.android.parkinglille.ui.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import application.ParkingApplication
import data.RecordsItem
import network.RepositoryManager

class ParkingViewModel : ViewModel() {

    private val  repositoryManager:RepositoryManager by lazy {
        ParkingApplication.serviceComponent.getRepositoryManager()
    }

    fun getParkingAvailability():LiveData<List<RecordsItem?>> = repositoryManager.getParkingAvailable()

}