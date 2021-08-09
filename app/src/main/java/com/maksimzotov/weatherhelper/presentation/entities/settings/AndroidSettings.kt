package com.maksimzotov.weatherhelper.presentation.entities.settings

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.maksimzotov.weatherhelper.data.android.presentation.AndroidSettingsTableNames

@Entity(tableName = AndroidSettingsTableNames.BOTTOM_NAVIGATION)
data class BottomNavigation(val isAble: Boolean)  {
    @PrimaryKey var id = 0
}

@Entity(tableName = AndroidSettingsTableNames.DARK_THEME)
data class DarkTheme(val isAble: Boolean)  {
    @PrimaryKey var id = 0
}

@Entity(tableName = AndroidSettingsTableNames.TEMPERATURE)
data class Temperature(val isAble: Boolean)  {
    @PrimaryKey var id = 0
}