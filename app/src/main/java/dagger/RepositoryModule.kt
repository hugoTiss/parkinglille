package dagger

import dao.ParkingDataDAO
import network.ParkingAvailabilityService
import network.RepositoryManager
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideRepositoryManager(parkingAvailabilityService: ParkingAvailabilityService,parkingDataDAO : ParkingDataDAO):RepositoryManager= RepositoryManager(parkingAvailabilityService,parkingDataDAO )

}