package com.maksimzotov.weatherhelper.presentation.ui.selection

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maksimzotov.weatherhelper.domain.entities.cities.City
import com.maksimzotov.weatherhelper.domain.usecases.loadcity.LoadCityUseCase
import kotlinx.coroutines.launch
import retrofit2.Response

class SelectionViewModel(private val loadCityUseCase: LoadCityUseCase) : ViewModel() {
    val response = MutableLiveData<Response<City>>()

    fun getCity(name: String) {
        viewModelScope.launch {
            response.value = loadCityUseCase.loadCity(name)
        }
    }
}