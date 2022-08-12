package gstv.dogapi.service

import gstv.dogapi.source.remote.BreedResponse
import retrofit2.http.GET

interface DogService {
    @GET("/breeds")
    suspend fun getBreeds(): List<BreedResponse>
}