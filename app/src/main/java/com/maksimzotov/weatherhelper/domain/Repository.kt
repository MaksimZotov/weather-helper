package com.maksimzotov.weatherhelper.domain

import com.maksimzotov.weatherhelper.domain.entities.City
import com.maksimzotov.weatherhelper.domain.entities.Filter
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface Repository {
    val currentFilter: Flow<Filter?>
    fun setCurrentFilter(filter: Filter)

    suspend fun loadCity(name: String): Response<City>
}