package gstv.dogapi.domain

import com.google.gson.annotations.SerializedName

data class FavouriteImage(
    @SerializedName("id") val id: Int,
    @SerializedName("image") val image: DogImage
)