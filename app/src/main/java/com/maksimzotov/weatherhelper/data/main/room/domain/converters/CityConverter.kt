package com.maksimzotov.weatherhelper.data.main.room.domain.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.maksimzotov.weatherhelper.domain.entities.Date
import com.maksimzotov.weatherhelper.domain.entities.Humidity
import com.maksimzotov.weatherhelper.domain.entities.Temperature

class CityConverter {
    private val gson = Gson()
    private val typeListOfDates = object : TypeToken<List<Date>>() {}.type
    private val typeListOfTemperatures = object : TypeToken<List<Temperature>>() {}.type
    private val typeHumidityList = object : TypeToken<List<Humidity>>() {}.type

    @TypeConverter
    fun fromDates(dates: List<Date>): String =
        gson.toJson(dates)

    @TypeConverter
    fun toDates(dates: String): List<Date> =
        gson.fromJson(dates, typeListOfDates)


    @TypeConverter
    fun fromTemperatures(temperatures: List<Temperature>): String =
        gson.toJson(temperatures)

    @TypeConverter
    fun toTemperatures(temperatures: String): List<Temperature> =
        gson.fromJson(temperatures, typeListOfTemperatures)


    @TypeConverter
    fun fromHumidityList(humidityList: List<Humidity>): String =
        gson.toJson(humidityList)

    @TypeConverter
    fun toHumidityList(humidityList: String): List<Humidity> =
        gson.fromJson(humidityList, typeHumidityList)
}