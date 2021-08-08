package com.maksimzotov.weatherhelper.data.android

import androidx.room.Database
import androidx.room.RoomDatabase
import com.maksimzotov.weatherhelper.presentation.entities.BottomNavigation
import com.maksimzotov.weatherhelper.presentation.entities.DarkTheme
import com.maksimzotov.weatherhelper.presentation.entities.Temperature

@Database(
    entities = [
        BottomNavigation::class,
        DarkTheme::class,
        Temperature::class
    ],
    version = 1
)
abstract class AndroidSettingsDatabase : RoomDatabase() {
    abstract fun androidSettingsDao(): AndroidSettingsDao
}