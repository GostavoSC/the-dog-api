package gstv.dogapi.source.remote

import com.google.gson.annotations.SerializedName

data class DogImageResponse(
    @SerializedName("id") val id: String,
    @SerializedName("url") val url: String,
)