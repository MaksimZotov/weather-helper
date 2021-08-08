package com.maksimzotov.weatherhelper.domain.usecases

import com.maksimzotov.weatherhelper.domain.entities.City
import com.maksimzotov.weatherhelper.domain.Repository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class LoadCityUseCase @Inject constructor(
    private val cityRepository: Repository,
    private val coroutineDispatcher: CoroutineDispatcher
) {
    suspend fun loadCity(name: String): Response<City> = withContext(coroutineDispatcher) {
        cityRepository.loadCity(name)
    }
}