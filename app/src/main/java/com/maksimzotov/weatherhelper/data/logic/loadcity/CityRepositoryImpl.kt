package com.maksimzotov.weatherhelper.data.logic.loadcity

import com.maksimzotov.weatherhelper.domain.entities.City
import com.maksimzotov.weatherhelper.domain.usecases.loadcity.CityRepository
import retrofit2.Response

class CityRepositoryImpl: CityRepository {
    override suspend fun loadCity(name: String): Response<City> {
        return RetrofitInstance.weatherApi.getCity(name)
    }
}