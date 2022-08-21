package gstv.dogapi.source.remote

import com.google.gson.annotations.SerializedName
import gstv.dogapi.domain.DogImage

data class FavouriteImageResponse(
    @SerializedName("image") val image: DogImage,
    @SerializedName("id") val id: Int
)