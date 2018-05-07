package data


import com.google.gson.annotations.SerializedName


data class ResponseParking(

	@field:SerializedName("records")
	val records: List<RecordsItem?>? = null
)