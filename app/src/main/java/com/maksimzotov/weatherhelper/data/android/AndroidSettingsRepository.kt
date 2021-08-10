package com.maksimzotov.weatherhelper.data.android

import com.maksimzotov.weatherhelper.presentation.entities.settings.Humidity
import com.maksimzotov.weatherhelper.presentation.entities.settings.BottomNavigation
import com.maksimzotov.weatherhelper.presentation.entities.settings.DarkTheme
import com.maksimzotov.weatherhelper.presentation.entities.settings.Temperature
import kotlinx.coroutines.flow.Flow

class AndroidSettingsRepository(private val androidSettingsDao: AndroidSettingsDao) {

    val bottomNavigation: Flow<BottomNavigation?> =
        androidSettingsDao.getBottomNavigation()

    val darkTheme: Flow<DarkTheme?> =
        androidSettingsDao.getDarkTheme()

    val temperature: Flow<Temperature?> =
        androidSettingsDao.getTemperature()

    val humidity: Flow<Humidity?> =
        androidSettingsDao.getHumidity()


    fun setBottomNavigation(bottomNavigation: BottomNavigation) =
        androidSettingsDao.setBottomNavigation(bottomNavigation)

    fun setDarkTheme(darkTheme: DarkTheme) =
        androidSettingsDao.setDarkTheme(darkTheme)

    fun setTemperature(temperature: Temperature) =
        androidSettingsDao.setTemperature(temperature)

    fun setHumidity(humidity: Humidity) =
        androidSettingsDao.setHumidity(humidity)
}