package com.maksimzotov.weatherhelper.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.maksimzotov.weatherhelper.data.main.room.domain.converters.CityConverter
import com.maksimzotov.weatherhelper.data.main.room.domain.MainTableNames
import java.io.Serializable

@Entity(tableName = MainTableNames.CITIES)
@TypeConverters(CityConverter::class)
data class City(
    @PrimaryKey var name: String,
    val dates: List<Date>,
    val temperatures: List<Temperature>
) : Serializable

