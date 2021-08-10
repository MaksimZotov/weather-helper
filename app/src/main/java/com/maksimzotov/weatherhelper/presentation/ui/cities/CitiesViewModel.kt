package com.maksimzotov.weatherhelper.presentation.ui.cities

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.maksimzotov.weatherhelper.domain.usecases.GetCitiesUseCase
import com.maksimzotov.weatherhelper.domain.usecases.GetCurrentFilterUseCase
import com.maksimzotov.weatherhelper.domain.usecases.RemoveCityUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class CitiesViewModel(
    private val getCitiesUseCase: GetCitiesUseCase,
    private val removeCityUseCase: RemoveCityUseCase,
    private val getCurrentFilterUseCase: GetCurrentFilterUseCase
) : ViewModel() {
    val cities = getCitiesUseCase.getCities().asLiveData()
    val filter = getCurrentFilterUseCase.getCurrentFilter().asLiveData()

    fun removeCity(position: Int) {
        val cityToRemove = cities.value?.get(position) ?: return
        viewModelScope.launch { removeCityUseCase.removeCity(cityToRemove) }
    }

    class Factory @Inject constructor(
        private val getCitiesUseCase: GetCitiesUseCase,
        private val removeCityUseCase: RemoveCityUseCase,
        private val getCurrentFilterUseCase: GetCurrentFilterUseCase
    ): ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return CitiesViewModel(
                getCitiesUseCase,
                removeCityUseCase,
                getCurrentFilterUseCase
            ) as T
        }
    }
}