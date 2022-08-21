package gstv.dogapi.source

import gstv.dogapi.core.utils.ResultWrapper
import gstv.dogapi.domain.Breed
import gstv.dogapi.domain.DogImage
import gstv.dogapi.domain.FavouriteImage

interface DogRepository {
    suspend fun getAllDogs(): ResultWrapper<List<Breed>>
    suspend fun getBreedImages(quantity: Int, breedId: String?): ResultWrapper<List<DogImage>>
    suspend fun saveFavoriteImage(image: DogImage): ResultWrapper<Unit>
    suspend fun getMyFavourites(): ResultWrapper<List<FavouriteImage>>
}