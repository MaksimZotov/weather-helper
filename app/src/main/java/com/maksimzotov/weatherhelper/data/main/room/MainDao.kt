package com.maksimzotov.weatherhelper.data.main.room

import androidx.room.*
import com.maksimzotov.weatherhelper.domain.entities.Filter
import com.maksimzotov.weatherhelper.data.main.room.domain.MainTableNames
import com.maksimzotov.weatherhelper.domain.entities.City
import kotlinx.coroutines.flow.Flow

@Dao
interface MainDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setCurrentFilter(filter: Filter)

    @Query("SELECT * FROM ${MainTableNames.CURRENT_FILTER}")
    fun getCurrentFilter(): Flow<Filter?>


    @Query("SELECT * FROM ${MainTableNames.CITIES} ORDER BY id ASC")
    fun getCities(): Flow<List<City>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addCity(city: City)

    @Delete
    fun removeCity(city: City)

    @Update()
    fun updateCity(city: City)
}