package com.maksimzotov.weatherhelper.domain.usecases.loadcity

import com.maksimzotov.weatherhelper.domain.entities.cities.City
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.Response

class LoadCityUseCase(
    private val cityRepository: CityRepository,
    private val coroutineDispatcher: CoroutineDispatcher
) {
    suspend fun loadCity(name: String): Response<City> = withContext(coroutineDispatcher) {
        cityRepository.loadCity(name)
    }
}