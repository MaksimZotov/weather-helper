package com.maksimzotov.weatherhelper.data.main

import com.maksimzotov.weatherhelper.data.main.retrofit.RetrofitInstance
import com.maksimzotov.weatherhelper.data.main.room.MainDao
import com.maksimzotov.weatherhelper.domain.Repository
import com.maksimzotov.weatherhelper.domain.entities.City
import com.maksimzotov.weatherhelper.domain.entities.Filter
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class RepositoryImpl(private val mainDao: MainDao): Repository {
    override val currentFilter: Flow<Filter?> =
        mainDao.getCurrentFilter()

    override fun setCurrentFilter(filter: Filter) =
        mainDao.setCurrentFilter(filter)

    override suspend fun loadCity(name: String): Response<City> =
        RetrofitInstance.weatherApi.getCity(name)

    override fun getCities(): Flow<List<City>?> =
        mainDao.getCities()

    override fun addCity(city: City) =
        mainDao.addCity(city)

    override fun removeCity(city: City) =
        mainDao.removeCity(city)

    override fun updateCity(city: City) =
        mainDao.updateCity(city)
}