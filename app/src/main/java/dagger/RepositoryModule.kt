package dagger

import network.ParkingAvailabilityService
import network.RepositoryManager
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideRepositoryManager(parkingAvailabilityService: ParkingAvailabilityService):RepositoryManager= RepositoryManager(parkingAvailabilityService)

}