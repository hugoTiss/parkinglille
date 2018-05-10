package data


import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "recordsItem")
data class RecordsItem(

        @PrimaryKey
        var keys: String,

        @field:SerializedName("recordid")
        val recordid: String,

        @field:SerializedName("datasetid")
        val datasetid: String,


        @Embedded
        @field:SerializedName("fields")
        val fields: Fields,

        @field:SerializedName("record_timestamp")
        val recordTimestamp: String
)