package com.maksimzotov.weatherhelper.data.android

import androidx.room.Database
import androidx.room.RoomDatabase
import com.maksimzotov.weatherhelper.presentation.entities.settings.BottomNavigation
import com.maksimzotov.weatherhelper.presentation.entities.settings.DarkTheme
import com.maksimzotov.weatherhelper.presentation.entities.settings.HumidityToShow
import com.maksimzotov.weatherhelper.presentation.entities.settings.TemperatureToShow

@Database(
    entities = [
        BottomNavigation::class,
        DarkTheme::class,
        TemperatureToShow::class,
        HumidityToShow::class
    ],
    version = 1
)
abstract class AndroidSettingsDatabase : RoomDatabase() {
    abstract fun androidSettingsDao(): AndroidSettingsDao
}