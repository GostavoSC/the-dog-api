package gstv.dogapi.source

import gstv.dogapi.core.NetworkUtils
import gstv.dogapi.core.utils.Mapper
import gstv.dogapi.core.utils.ResultWrapper
import gstv.dogapi.core.utils.mapWith
import gstv.dogapi.core.utils.safeApiCall
import gstv.dogapi.domain.Breed
import gstv.dogapi.source.remote.BreedResponse

class DogRepositoryImpl(
    private val breedsResponseMapper: Mapper<BreedResponse, Breed>
) : DogRepository {
    private val service = NetworkUtils.apiService

    override suspend fun getAllDogs(): ResultWrapper<List<Breed>> = safeApiCall {
        service.getBreeds().mapWith(breedsResponseMapper)
    }

}