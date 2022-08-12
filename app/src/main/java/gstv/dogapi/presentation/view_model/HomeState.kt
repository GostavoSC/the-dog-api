package gstv.dogapi.presentation.view_model

import gstv.dogapi.domain.Breed
import gstv.dogapi.domain.DogImage

data class HomeState(
    val breeds: List<Breed> = emptyList(),
    val images: List<DogImage> = emptyList()
)
