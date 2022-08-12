package gstv.dogapi.source.remote

import com.google.gson.annotations.SerializedName

data class CategoryResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String
)