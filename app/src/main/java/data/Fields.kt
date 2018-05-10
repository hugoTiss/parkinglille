package data


import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.TypeConverters
import com.google.gson.annotations.SerializedName
import dao.DataTypeConverter

@Entity(tableName = "fields")
data class Fields(


        @field:SerializedName("aff")
        val aff: String,

        @field:SerializedName("ville")
        val ville: String,

        @field:SerializedName("max")
        val max: Int,

        @field:SerializedName("libelle")
        val libelle: String,

        @field:SerializedName("adresse")
        val adresse: String,

        @Embedded
        @field:SerializedName("geometry")
        val geometry: Geometry,

        @TypeConverters(DataTypeConverter::class)
        @field:SerializedName("coordgeo")
        val coordgeo: List<Double>,

        @PrimaryKey
        @field:SerializedName("id")
        val id: String,

        @field:SerializedName("etat")
        val etat: String,

        @field:SerializedName("dispo")
        val dispo: Int,

        @field:SerializedName("datemaj")
        val datemaj: String
)