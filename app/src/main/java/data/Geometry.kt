package data


import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "geometry")
data class Geometry(


        @field:SerializedName("coordinates")
        val coordinates: List<Double?>?,

        @field:SerializedName("type")
        val type: String
)