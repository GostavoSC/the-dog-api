package gstv.dogapi.service

import gstv.dogapi.core.HEADER
import gstv.dogapi.source.remote.BreedResponse
import gstv.dogapi.source.remote.DogImageResponse
import gstv.dogapi.source.remote.FavouriteImageResponse
import gstv.dogapi.source.remote.FavouriteImageResponseBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface DogService {
    @GET("breeds")
    suspend fun getBreeds(): List<BreedResponse>

    @GET("images/search")
    suspend fun getBreedImages(
        @Query("limit") quantity: Int,
        @Query("breed_id") breedId: String?
    ): List<DogImageResponse>

    @POST("favourites")
    suspend fun saveImageAsFavourite(
        @Body body: FavouriteImageResponseBody,
        @Header(HEADER) apiKey: String
    )

    @GET("favourites")
    suspend fun getMyFavourites(@Header(HEADER) apiKey: String): List<FavouriteImageResponse>
}