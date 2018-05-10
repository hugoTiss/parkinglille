package network


import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import dao.ParkingDataBase
import dao.ParkingDataDAO
import data.RecordsItem
import data.ResponseParking
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class RepositoryManager @Inject constructor(private val parkingAvailabilityService: ParkingAvailabilityService,private val parkingDataDAO: ParkingDataDAO) {

    companion object {
        var isInit=false
    }

    var parkingAvailable = MutableLiveData<List<RecordsItem?>>()

    var parkingServiceError= MutableLiveData<Boolean>()
    fun getParkingAvailable(): LiveData<List<RecordsItem>> {
        if (!isInit)initData()
        return parkingDataDAO.getAllParkingAvailable()
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
                    isInit=true

                    parkingDataDAO.insetData(changePrivateKeyParking(this.requireNoNulls()))

                }
            }

        })
    }
    fun changePrivateKeyParking( list:List<RecordsItem>):List<RecordsItem>{
        for (item in list)
        {
            item.keys=item.fields.id
        }
        return list
    }
    fun initData()=callParkingAvailable()

}