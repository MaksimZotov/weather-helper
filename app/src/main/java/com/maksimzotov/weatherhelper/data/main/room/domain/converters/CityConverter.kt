package com.maksimzotov.weatherhelper.data.main.room.domain.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.maksimzotov.weatherhelper.domain.entities.Temperature

class CityConverter {
    private val gson = Gson()
    private val type = object : TypeToken<Map<String, Temperature>>() {}.type

    @TypeConverter
    fun fromTemperatures(temperatures: Map<String, Temperature>): String =
        gson.toJson(temperatures)

    @TypeConverter
    fun toTemperatures(temperatures: String): Map<String, Temperature> =
        gson.fromJson(temperatures, type)
}