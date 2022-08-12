package gstv.dogapi.source

import gstv.dogapi.core.NetworkUtils
import gstv.dogapi.core.utils.Mapper
import gstv.dogapi.core.utils.ResultWrapper
import gstv.dogapi.core.utils.mapWith
import gstv.dogapi.core.utils.safeApiCall
import gstv.dogapi.domain.Breed
import gstv.dogapi.domain.DogImage
import gstv.dogapi.source.remote.BreedResponse
import gstv.dogapi.source.remote.DogImageResponse

class DogRepositoryImpl(
    private val breedsResponseMapper: Mapper<BreedResponse, Breed>,
    private val dogImageResponseMapper: Mapper<DogImageResponse, DogImage>
) : DogRepository {
    private val service = NetworkUtils.apiService

    override suspend fun getAllDogs(): ResultWrapper<List<Breed>> = safeApiCall {
        service.getBreeds().mapWith(breedsResponseMapper)
    }

    override suspend fun getRandomDogsImage(quantity: Int): ResultWrapper<List<DogImage>> =
        safeApiCall {
            service.getRandomDogsImage(quantity = quantity).mapWith(dogImageResponseMapper)
        }

}