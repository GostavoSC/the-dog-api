package gstv.dogapi.source

import gstv.dogapi.core.utils.ResultWrapper
import gstv.dogapi.domain.Breed
import gstv.dogapi.domain.DogImage

interface DogRepository {
    suspend fun getAllDogs(): ResultWrapper<List<Breed>>
    suspend fun getRandomDogsImage(quantity:Int): ResultWrapper<List<DogImage>>
}