package com.maksimzotov.weatherhelper.data.android

import androidx.room.*
import com.maksimzotov.weatherhelper.presentation.entities.AndroidSettings
import com.maksimzotov.weatherhelper.presentation.entities.BottomNavigation
import com.maksimzotov.weatherhelper.presentation.entities.DarkTheme
import com.maksimzotov.weatherhelper.presentation.entities.Temperature
import kotlinx.coroutines.flow.Flow

@Dao
interface AndroidSettingsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setBottomNavigation(bottomNavigation: BottomNavigation)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setDarkTheme(darkTheme: DarkTheme)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setTemperature(temperature: Temperature)

    @Query("SELECT * FROM ${AndroidSettings.TABLE_NAME_BOTTOM_NAVIGATION}")
    fun getBottomNavigation(): Flow<BottomNavigation?>

    @Query("SELECT * FROM ${AndroidSettings.TABLE_NAME_DARK_THEME}")
    fun getDarkTheme(): Flow<DarkTheme?>

    @Query("SELECT * FROM ${AndroidSettings.TABLE_NAME_TEMPERATURE}")
    fun getTemperature(): Flow<Temperature?>
}