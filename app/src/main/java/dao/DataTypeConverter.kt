package dao

import android.arch.persistence.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DataTypeConverter {


        @TypeConverter
        fun fromModelToString(value: List<Double?>): String =  Gson().toJson(value)

        @TypeConverter
        fun fromStringToModel(value: String): List<Double?> {

            return Gson().fromJson(value, object : TypeToken<List<Double?>>() {}.type)
        }


}