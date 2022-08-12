package gstv.dogapi.presentation.view_model

import gstv.dogapi.domain.Breed
import gstv.dogapi.domain.Category
import gstv.dogapi.domain.DogImage

data class HomeState(
    val breeds: List<Breed> = emptyList(),
    val images: List<DogImage> = emptyList(),
    val categories: List<Category> = emptyList(),
    val currentBreed: Breed? = null,
    val currentImage: DogImage? = null
)
