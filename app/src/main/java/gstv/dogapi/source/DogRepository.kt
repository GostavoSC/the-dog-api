package gstv.dogapi.source

import gstv.dogapi.core.utils.ResultWrapper
import gstv.dogapi.domain.Breed

interface DogRepository {
    suspend fun getAllDogs(): ResultWrapper<List<Breed>>
}