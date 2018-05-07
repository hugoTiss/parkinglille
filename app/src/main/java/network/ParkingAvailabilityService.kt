package network

import data.ResponseParking
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ParkingAvailabilityService {

    @GET("search")
    fun availabilityParking(@Query("dataset")dataset:String,@Query("rows") rows:Int): Call<ResponseParking>



}