package gstv.dogapi.service

import gstv.dogapi.source.remote.BreedResponse
import gstv.dogapi.source.remote.DogImageResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface DogService {
    @GET("breeds")
    suspend fun getBreeds(): List<BreedResponse>

    @GET("images/search")
    suspend fun getRandomDogsImage(@Query("limit") quantity: Int): List<DogImageResponse>
}