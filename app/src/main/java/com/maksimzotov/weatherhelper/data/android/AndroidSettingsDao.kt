package com.maksimzotov.weatherhelper.data.android

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.maksimzotov.weatherhelper.presentation.entities.settings.BottomNavigation
import com.maksimzotov.weatherhelper.presentation.entities.settings.DarkTheme
import com.maksimzotov.weatherhelper.presentation.entities.settings.AndroidSettingsTableNames
import com.maksimzotov.weatherhelper.presentation.entities.settings.Temperature
import kotlinx.coroutines.flow.Flow

@Dao
interface AndroidSettingsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setBottomNavigation(bottomNavigation: BottomNavigation)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setDarkTheme(darkTheme: DarkTheme)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setTemperature(temperature: Temperature)

    @Query("SELECT * FROM ${AndroidSettingsTableNames.BOTTOM_NAVIGATION}")
    fun getBottomNavigation(): Flow<BottomNavigation?>

    @Query("SELECT * FROM ${AndroidSettingsTableNames.DARK_THEME}")
    fun getDarkTheme(): Flow<DarkTheme?>

    @Query("SELECT * FROM ${AndroidSettingsTableNames.TEMPERATURE}")
    fun getTemperature(): Flow<Temperature?>
}