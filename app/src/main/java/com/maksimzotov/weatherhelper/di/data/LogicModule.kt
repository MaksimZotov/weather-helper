package com.maksimzotov.weatherhelper.di.data

import com.maksimzotov.weatherhelper.data.logic.loadcity.CityRepositoryImpl
import com.maksimzotov.weatherhelper.domain.usecases.loadcity.CityRepository
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
class LogicModule {

    @Provides
    fun provideCityRepository(): CityRepository {
        return CityRepositoryImpl()
    }

    @Provides
    fun provideCoroutineDispatcherForLoadCityUseCase(): CoroutineDispatcher {
        return Dispatchers.IO
    }
}