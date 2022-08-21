package gstv.dogapi.source.remote

import com.google.gson.annotations.SerializedName

data class FavouriteImageResponseBody(
    @SerializedName("image_id") val imageId: String?
)
