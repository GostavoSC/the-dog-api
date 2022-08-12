package gstv.dogapi.presentation.view_model

import gstv.dogapi.domain.Breed

data class HomeState(
    val  breeds: List<Breed>? = null
)
