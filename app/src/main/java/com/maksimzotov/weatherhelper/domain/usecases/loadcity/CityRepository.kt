package com.maksimzotov.weatherhelper.domain.usecases.loadcity

import com.maksimzotov.weatherhelper.domain.entities.City
import retrofit2.Response

interface CityRepository {
    suspend fun loadCity(name: String): Response<City>
}