package com.maksimzotov.weatherhelper.presentation.ui.selection

import androidx.lifecycle.*
import com.maksimzotov.weatherhelper.domain.entities.City
import com.maksimzotov.weatherhelper.domain.usecases.AddCityUseCase
import com.maksimzotov.weatherhelper.domain.usecases.GetCitiesUseCase
import com.maksimzotov.weatherhelper.domain.usecases.LoadCityUseCase
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

class SelectionViewModel(
    private val loadCityUseCase: LoadCityUseCase,
    private val addCityUseCase: AddCityUseCase,
    private val getCitiesUseCase: GetCitiesUseCase
) : ViewModel() {
    val loadedCity = MutableLiveData<Response<City>>()
    val cities = getCitiesUseCase.getCities().asLiveData()

    val popBackstack = MutableLiveData(false)
    val error = MutableLiveData(false)

    fun addCity(name: String) = viewModelScope.launch {
        try {
            loadedCity.value = loadCityUseCase.loadCity(name)
        } catch (ex: Exception) {
            error.value = true
        }
        val city = loadedCity.value?.body() ?: return@launch
        addCityUseCase.addCity(city)
        popBackstack.value = true
    }

    class Factory @Inject constructor(
        private val loadCityUseCase: LoadCityUseCase,
        private val addCityUseCase: AddCityUseCase,
        private val getCitiesUseCase: GetCitiesUseCase
    ): ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return SelectionViewModel(
                loadCityUseCase,
                addCityUseCase,
                getCitiesUseCase
            ) as T
        }
    }
}