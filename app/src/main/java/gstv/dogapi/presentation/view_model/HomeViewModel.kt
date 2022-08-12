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
            ChoicesEnum.BY_CATEGORY -> {
                getCategories()
            }
            else -> {
                getAllBreeds()
            }
        }
    }

    fun getBreedById(id: String) {
        _homeState.update {
            it.copy(
                currentBreed = it.breeds.first { breed -> breed.id == id }
            )
        }
    }

    private fun getCategories() {
        viewModelScope.launch {
            when (val result = repository.getCategories()) {
                is ResultWrapper.Success -> {
                    _homeState.update {
                        it.copy(
                            categories = result.value
                        )
                    }
                }
                else -> {
                    //todo
                }
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

    fun getRandomImages() {
        viewModelScope.launch {
            when (val result = repository.getRandomDogsImage(3)) {
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

    fun updateCurrentBreed(breed: Breed) {
        _homeState.update {
            it.copy(
                currentBreed = breed
            )
        }
    }

}