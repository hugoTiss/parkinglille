package dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import data.RecordsItem

@Dao
interface ParkingDataDAO {

    @Query("SELECT * from recordsItem")
    fun getAllParkingAvailable():LiveData<List<RecordsItem>>

    @Insert(onConflict = REPLACE)
    fun insetData(parkingAvailable:List<RecordsItem>)
}