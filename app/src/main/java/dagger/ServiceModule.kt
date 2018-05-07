package dagger

import network.ParkingAvailabilityService
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
class ServiceModule {


    @Provides
    fun provideParkingService( retrofit: Retrofit):ParkingAvailabilityService{
        return retrofit.create(ParkingAvailabilityService::class.java)
    }

}