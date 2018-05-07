package data


import com.google.gson.annotations.SerializedName

data class Fields(

	@field:SerializedName("aff")
	val aff: String? = null,

	@field:SerializedName("ville")
	val ville: String? = null,

	@field:SerializedName("max")
	val max: Int? = null,

	@field:SerializedName("libelle")
	val libelle: String? = null,

	@field:SerializedName("adresse")
	val adresse: String? = null,

	@field:SerializedName("geometry")
	val geometry: Geometry? = null,

	@field:SerializedName("coordgeo")
	val coordgeo: List<Double?>? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("etat")
	val etat: String? = null,

	@field:SerializedName("dispo")
	val dispo: Int? = null,

	@field:SerializedName("datemaj")
	val datemaj: String? = null
)