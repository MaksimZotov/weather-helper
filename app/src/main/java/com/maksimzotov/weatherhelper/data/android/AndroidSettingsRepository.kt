package com.maksimzotov.weatherhelper.data.android

import kotlinx.coroutines.flow.Flow

class AndroidSettingsRepository(private val androidSettingsDao: AndroidSettingsDao) {

    val bottomNavigation: Flow<BottomNavigation?> = androidSettingsDao.getBottomNavigation()
    val darkTheme: Flow<DarkTheme?> = androidSettingsDao.getDarkTheme()
    val temperature: Flow<Temperature?> = androidSettingsDao.getTemperature()

    fun setBottomNavigation(bottomNavigation: BottomNavigation) =
        androidSettingsDao.setBottomNavigation(bottomNavigation)

    fun setDarkTheme(darkTheme: DarkTheme) =
        androidSettingsDao.setDarkTheme(darkTheme)

    fun setTemperature(temperature: Temperature) =
        androidSettingsDao.setTemperature(temperature)
}