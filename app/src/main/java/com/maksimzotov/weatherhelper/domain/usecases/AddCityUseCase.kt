package com.maksimzotov.weatherhelper.domain.usecases

import com.maksimzotov.weatherhelper.domain.Repository
import com.maksimzotov.weatherhelper.domain.entities.City
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AddCityUseCase @Inject constructor(
    private val repository: Repository,
    private val coroutineDispatcher: CoroutineDispatcher
) {
    suspend fun addCity(city: City) = withContext(coroutineDispatcher) {
        repository.addCity(city)
    }
}