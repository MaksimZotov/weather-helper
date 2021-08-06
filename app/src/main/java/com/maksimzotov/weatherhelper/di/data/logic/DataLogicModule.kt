package com.maksimzotov.weatherhelper.di.data.logic

import com.maksimzotov.weatherhelper.di.data.logic.loadcity.DataLogicLoadCityModule
import dagger.Module

@Module(includes = [DataLogicLoadCityModule::class])
class DataLogicModule