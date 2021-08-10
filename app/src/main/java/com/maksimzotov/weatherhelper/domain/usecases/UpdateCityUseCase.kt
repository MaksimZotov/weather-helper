package com.maksimzotov.weatherhelper.domain.usecases

import com.maksimzotov.weatherhelper.domain.Repository
import com.maksimzotov.weatherhelper.domain.entities.City
import javax.inject.Inject

class UpdateCityUseCase @Inject constructor(
    private val repository: Repository
) {
    fun updateCity(city: City) =
        repository.updateCity(city)
}