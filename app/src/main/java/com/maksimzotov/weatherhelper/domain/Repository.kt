package com.maksimzotov.weatherhelper.domain

import com.maksimzotov.weatherhelper.domain.entities.City
import com.maksimzotov.weatherhelper.domain.entities.Filter
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface Repository {
    suspend fun loadCity(name: String): Response<City>

    val currentFilter: Flow<Filter?>
    fun setCurrentFilter(filter: Filter)

    fun getCities(): Flow<List<City>?>
    fun addCity(city: City)
    fun removeCity(city: City)
    fun updateCity(city: City)
}