package gstv.dogapi.presentation.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import gstv.dogapi.core.utils.ResultWrapper
import gstv.dogapi.source.DogRepository
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: DogRepository) : ViewModel() {

    fun getBreeds() {
        viewModelScope.launch {
            when (val result = repository.getAllDogs()) {
                is ResultWrapper.Success -> {
                    result
                }
                else -> {
                    //todo
                }
            }
        }
    }

}