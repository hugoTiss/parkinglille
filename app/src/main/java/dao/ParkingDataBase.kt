package dao

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import data.RecordsItem


@Database(entities = arrayOf(RecordsItem::class),version = 1)
@TypeConverters(DataTypeConverter::class)
abstract class ParkingDataBase : RoomDatabase() {

    abstract fun parkingDao():ParkingDataDAO
}