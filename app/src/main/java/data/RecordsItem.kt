package data


import com.google.gson.annotations.SerializedName


data class RecordsItem(

	@field:SerializedName("recordid")
	val recordid: String? = null,

	@field:SerializedName("datasetid")
	val datasetid: String? = null,

	@field:SerializedName("geometry")
	val geometry: Geometry? = null,

	@field:SerializedName("fields")
	val fields: Fields? = null,

	@field:SerializedName("record_timestamp")
	val recordTimestamp: String? = null
)