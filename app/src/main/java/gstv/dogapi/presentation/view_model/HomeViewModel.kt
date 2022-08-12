package gstv.dogapi.presentation.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import gstv.dogapi.core.utils.ResultWrapper
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

    fun getBreeds() {
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

}