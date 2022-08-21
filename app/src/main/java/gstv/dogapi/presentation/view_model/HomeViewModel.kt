package gstv.dogapi.presentation.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import gstv.dogapi.core.utils.ChoicesEnum
import gstv.dogapi.core.utils.ResultWrapper
import gstv.dogapi.domain.Breed
import gstv.dogapi.source.DogRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: DogRepository) : ViewModel() {

    private val _homeState = MutableStateFlow(
        HomeState()
    )
    val homeState = _homeState.asStateFlow()

    fun getBreeds(choicesEnum: ChoicesEnum) {
        when (choicesEnum) {
            ChoicesEnum.MY_FAVOURITES -> {
                //coming soon
            }
            else -> {
                getAllBreeds()
            }
        }
    }

    private fun getAllBreeds() {
        viewModelScope.launch {
            when (val result = repository.getAllDogs()) {
                is ResultWrapper.Success -> {
                    _homeState.update {
                        it.copy(
                            breeds = result.value
                        )
                    }
                }
                else -> {
                    //todo
                }
            }
        }
    }

    fun getBreedImages() {
        viewModelScope.launch {
            when (val result = repository.getBreedImages(5, _homeState.value.currentBreed?.id)) {
                is ResultWrapper.Success -> {
                    _homeState.update {
                        it.copy(
                            images = result.value
                        )
                    }
                }
                else -> {
                    //todo
                }
            }
        }
    }


    fun favoriteImage() {
        viewModelScope.launch {
            when (val result = repository.saveFavoriteImage(
                _homeState.value.images.first()
            )) {
                is ResultWrapper.Success -> {
                    //do anything when is success
                }
                else -> {
                    //todo
                }
            }
        }
    }

    fun updateCurrentBreed(breed: Breed) {
        _homeState.update {
            it.copy(
                currentBreed = breed
            )
        }
    }

    fun getMyFavourites() {
        viewModelScope.launch {
            when (val result = repository.getMyFavourites()) {
                is ResultWrapper.Success -> {
                    _homeState.update {
                        it.copy(
                            favouriteImages = result.value
                        )
                    }
                }
                else -> {
                    //todo
                }
            }
        }
    }

}