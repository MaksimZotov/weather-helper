package com.maksimzotov.weatherhelper.data.android

import com.maksimzotov.weatherhelper.presentation.entities.settings.HumidityToShow
import com.maksimzotov.weatherhelper.presentation.entities.settings.BottomNavigation
import com.maksimzotov.weatherhelper.presentation.entities.settings.DarkTheme
import com.maksimzotov.weatherhelper.presentation.entities.settings.TemperatureToShow
import kotlinx.coroutines.flow.Flow

class AndroidSettingsRepository(private val androidSettingsDao: AndroidSettingsDao) {

    val bottomNavigation: Flow<BottomNavigation?> =
        androidSettingsDao.getBottomNavigation()

    val darkTheme: Flow<DarkTheme?> =
        androidSettingsDao.getDarkTheme()

    val temperatureToShow: Flow<TemperatureToShow?> =
        androidSettingsDao.getTemperatureToShow()

    val humidityToShow: Flow<HumidityToShow?> =
        androidSettingsDao.getHumidityToShow()


    fun setBottomNavigation(bottomNavigation: BottomNavigation) =
        androidSettingsDao.setBottomNavigation(bottomNavigation)

    fun setDarkTheme(darkTheme: DarkTheme) =
        androidSettingsDao.setDarkTheme(darkTheme)

    fun setTemperatureToShow(temperatureToShow: TemperatureToShow) =
        androidSettingsDao.setTemperatureToShow(temperatureToShow)

    fun setHumidityToShow(humidityToShow: HumidityToShow) =
        androidSettingsDao.setHumidityToShow(humidityToShow)
}