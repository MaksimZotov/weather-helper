package com.maksimzotov.weatherhelper.data.android

import androidx.room.Entity
import androidx.room.PrimaryKey

object AndroidSettings {
    const val TABLE_NAME_BOTTOM_NAVIGATION = "android_settings_bottom_navigation"
    const val TABLE_NAME_DARK_THEME = "android_settings_dark_theme"
    const val TABLE_NAME_TEMPERATURE = "android_settings_temperature"
}

@Entity(tableName = AndroidSettings.TABLE_NAME_BOTTOM_NAVIGATION)
data class BottomNavigation(val isAble: Boolean)  {
    @PrimaryKey var id: Int = 0
}

@Entity(tableName = AndroidSettings.TABLE_NAME_DARK_THEME)
data class DarkTheme(val isAble: Boolean)  {
    @PrimaryKey var id: Int = 0
}

@Entity(tableName = AndroidSettings.TABLE_NAME_TEMPERATURE)
data class Temperature(val isAble: Boolean)  {
    @PrimaryKey var id: Int = 0
}