package gstv.dogapi.presentation.view_model

import gstv.dogapi.domain.Breed
import gstv.dogapi.domain.DogImage
import gstv.dogapi.domain.FavouriteImage

data class HomeState(
    val breeds: List<Breed> = emptyList(),
    val images: List<DogImage> = emptyList(),
    val favouriteImages: List<FavouriteImage> = emptyList(),
    val currentBreed: Breed? = null
)
