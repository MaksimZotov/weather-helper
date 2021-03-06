package com.maksimzotov.weatherhelper.presentation.ui.cities

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.maksimzotov.weatherhelper.domain.entities.City
import com.maksimzotov.weatherhelper.domain.usecases.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class CitiesViewModel(
    private val getCitiesUseCase: GetCitiesUseCase,
    private val removeCityUseCase: RemoveCityUseCase,
    private val getCurrentFilterUseCase: GetCurrentFilterUseCase,
    private val updateCityUseCase: UpdateCityUseCase,
    private val loadCityUseCase: LoadCityUseCase
) : ViewModel() {
    val cities = getCitiesUseCase.getCities().asLiveData()
    val filter = getCurrentFilterUseCase.getCurrentFilter().asLiveData()

    private var _citiesAreUpdated = true
    val citiesAreUpdated get() = _citiesAreUpdated

    private var _userMustBeNotifiedAboutUpdatedCities = false
    val userMustBeNotifiedAboutUpdatedCities: Boolean get() {
        if (_userMustBeNotifiedAboutUpdatedCities) {
            _userMustBeNotifiedAboutUpdatedCities = false
            return true
        }
        return false
    }

    fun updateCities(cities: List<City>) {
        _citiesAreUpdated = false
        viewModelScope.launch {
            for (city in cities) {
                if (!citiesAreUpdated) {
                    try {
                        val updatedCity = loadCityUseCase.loadCity(city.name).body() ?: continue
                        updateCityUseCase.updateCity(updatedCity)
                    } catch (ex: Exception) {
                        updateCityUseCase.updateCity(city)
                    }
                }
            }
            _citiesAreUpdated = true
            _userMustBeNotifiedAboutUpdatedCities = true
        }
    }

    fun removeCity(position: Int) {
        val cityToRemove = cities.value?.get(position) ?: return
        viewModelScope.launch { removeCityUseCase.removeCity(cityToRemove) }
    }

    class Factory @Inject constructor(
        private val getCitiesUseCase: GetCitiesUseCase,
        private val removeCityUseCase: RemoveCityUseCase,
        private val getCurrentFilterUseCase: GetCurrentFilterUseCase,
        private val updateCityUseCase: UpdateCityUseCase,
        private val loadCityUseCase: LoadCityUseCase
    ): ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return CitiesViewModel(
                getCitiesUseCase,
                removeCityUseCase,
                getCurrentFilterUseCase,
                updateCityUseCase,
                loadCityUseCase
            ) as T
        }
    }
}