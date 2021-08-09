package com.maksimzotov.weatherhelper.data.main.room.domain.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.maksimzotov.weatherhelper.domain.entities.City

class CityConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromCity(city: City): String = gson.toJson(city)

    @TypeConverter
    fun toCity(city: String): City = gson.fromJson(city, City::class.java)
}