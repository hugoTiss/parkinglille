package dagger

import android.app.Application
import android.arch.persistence.room.Room
import dao.ParkingDataBase

@Module
class RoomModule {

    @Provides
    fun provideAppDataBase(app:Application):ParkingDataBase=
        Room.databaseBuilder(app.applicationContext, ParkingDataBase::class.java, "my-db").allowMainThreadQueries().build()

    @Provides
    fun provideDAO(database: ParkingDataBase)=database.parkingDao()

}