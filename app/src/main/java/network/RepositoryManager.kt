package network


import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import data.RecordsItem
import data.ResponseParking
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import util.Constants
import javax.inject.Inject

class RepositoryManager @Inject constructor(private val parkingAvailabilityService: ParkingAvailabilityService) {


    var parkingAvailable = MutableLiveData<List<RecordsItem?>>()

    var parkingServiceError= MutableLiveData<Boolean>()
    fun getParkingAvailable(): LiveData<List<RecordsItem?>> {
        callParkingAvailable()
        return parkingAvailable
    }

    fun getParkingServiceError():LiveData<Boolean> = parkingServiceError


    fun callParkingAvailable() {
        var callParkingService = parkingAvailabilityService.availabilityParking("disponibilite-parkings", -1)
        callParkingService.enqueue(object : Callback<ResponseParking> {
            override fun onFailure(call: Call<ResponseParking>?, t: Throwable?) {
                parkingServiceError.value=true
            }

            override fun onResponse(call: Call<ResponseParking>?, response: Response<ResponseParking>?) {


                response?.body()?.records?.run {
                    parkingAvailable.value = this
                    parkingServiceError.value=false
                }

            }

        })
    }

}