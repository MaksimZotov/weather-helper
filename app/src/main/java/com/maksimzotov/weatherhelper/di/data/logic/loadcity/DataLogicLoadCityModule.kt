package com.maksimzotov.weatherhelper.di.data.logic.loadcity

import com.maksimzotov.weatherhelper.data.logic.loadcity.CityRepositoryImpl
import com.maksimzotov.weatherhelper.domain.usecases.loadcity.CityRepository
import dagger.Module
import dagger.Provides

@Module
class DataLogicLoadCityModule {

    @Provides
    fun provideCityRepository(): CityRepository {
        return CityRepositoryImpl()
    }
}