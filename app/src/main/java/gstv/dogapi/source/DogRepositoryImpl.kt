package gstv.dogapi.source

import gstv.dogapi.core.API_KEY
import gstv.dogapi.core.NetworkUtils
import gstv.dogapi.core.utils.Mapper
import gstv.dogapi.core.utils.ResultWrapper
import gstv.dogapi.core.utils.mapWith
import gstv.dogapi.core.utils.safeApiCall
import gstv.dogapi.domain.Breed
import gstv.dogapi.domain.DogImage
import gstv.dogapi.domain.FavouriteImage
import gstv.dogapi.source.remote.BreedResponse
import gstv.dogapi.source.remote.DogImageResponse
import gstv.dogapi.source.remote.FavouriteImageResponse
import gstv.dogapi.source.remote.FavouriteImageResponseBody

class DogRepositoryImpl(
    private val breedsResponseMapper: Mapper<BreedResponse, Breed>,
    private val dogImageResponseMapper: Mapper<DogImageResponse, DogImage>,
    private val favouriteImageResponseMapper: Mapper<FavouriteImageResponse, FavouriteImage>
) : DogRepository {
    private val service = NetworkUtils.apiService

    override suspend fun getAllDogs(): ResultWrapper<List<Breed>> = safeApiCall {
        service.getBreeds().mapWith(breedsResponseMapper)
    }

    override suspend fun getBreedImages(
        quantity: Int,
        breedId: String?
    ): ResultWrapper<List<DogImage>> =
        safeApiCall {
            service.getBreedImages(quantity = quantity, breedId = breedId)
                .mapWith(dogImageResponseMapper)
        }

    override suspend fun saveFavoriteImage(image: DogImage): ResultWrapper<Unit> =
        safeApiCall {
            service.saveImageAsFavourite(FavouriteImageResponseBody(image.id), API_KEY)
        }

    override suspend fun getMyFavourites(): ResultWrapper<List<FavouriteImage>> = safeApiCall {
        service.getMyFavourites(API_KEY).mapWith(favouriteImageResponseMapper)
    }
}