package gstv.dogapi.source.remote

import com.google.gson.annotations.SerializedName

data class BreedResponse(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("origin") val origin: String,
    @SerializedName("lifeSpan") val lifeSpan: String,
    @SerializedName("temperament") val temperament: String
)
