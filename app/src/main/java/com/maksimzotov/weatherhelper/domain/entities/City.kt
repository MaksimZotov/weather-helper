package com.maksimzotov.weatherhelper.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.maksimzotov.weatherhelper.data.main.room.domain.converters.CityConverter
import com.maksimzotov.weatherhelper.data.main.room.domain.MainTableNames

@Entity(tableName = MainTableNames.CITIES)
@TypeConverters(CityConverter::class)
data class City(
    val name: String,
    val temperatures: Map<String, Temperature>
) {
    @PrimaryKey(autoGenerate = true) var id = 0
}

