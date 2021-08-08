package com.maksimzotov.weatherhelper.data.main.room

import androidx.room.*
import com.maksimzotov.weatherhelper.domain.entities.Filter
import com.maksimzotov.weatherhelper.domain.entities.MainTableNames
import kotlinx.coroutines.flow.Flow

@Dao
interface MainDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setCurrentFilter(filter: Filter)

    @Query("SELECT * FROM ${MainTableNames.CURRENT_FILTER}")
    fun getCurrentFilter(): Flow<Filter?>
}