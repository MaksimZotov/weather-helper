package com.maksimzotov.weatherhelper.presentation.ui.selection

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.maksimzotov.weatherhelper.domain.entities.cities.City
import com.maksimzotov.weatherhelper.domain.usecases.loadcity.LoadCityUseCase
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

class SelectionViewModel(private val loadCityUseCase: LoadCityUseCase) : ViewModel() {
    val response = MutableLiveData<Response<City>>()

    fun getCity(name: String) {
        viewModelScope.launch {
            response.value = loadCityUseCase.loadCity(name)
        }
    }

    class Factory @Inject constructor(
        private val loadCityUseCase: LoadCityUseCase
    ): ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return SelectionViewModel(loadCityUseCase) as T
        }
    }
}