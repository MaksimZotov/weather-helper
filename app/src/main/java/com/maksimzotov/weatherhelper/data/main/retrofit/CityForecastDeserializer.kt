package com.maksimzotov.weatherhelper.data.main.retrofit

import com.google.gson.*
import com.maksimzotov.weatherhelper.domain.entities.City
import com.maksimzotov.weatherhelper.domain.entities.Temperature
import java.lang.IllegalArgumentException
import java.lang.reflect.Type
import kotlin.math.roundToInt

class CityForecastDeserializer : JsonDeserializer<City> {
    private val fromKelvin = 273.15
    private val separatorIndex = 10

    private val mainRegex = Regex(
        "(\\d{4}-\\d{2}-\\d{2} 00:00:00)|\"temp_min\":[\\d\\.]+,\"temp_max\":[\\d\\.]+"
    )
    private val dateRegex = Regex("\\d{4}-\\d{2}-\\d{2} 00:00:00")
    private val separatorRegex = Regex(":|,")

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): City {
        val jsonTemp = json ?: throw IllegalArgumentException("json must not be null")
        val jsonData = jsonTemp.toString()
        val name = jsonTemp.asJsonObject.get("city").asJsonObject.get("name").toString()
        return City(name, getTemperaturesFromJson(jsonData))
    }

    fun getTemperaturesFromJson(jsonData: String): Map<String, Temperature> {
        val matches = mainRegex.findAll(jsonData)
        val parsedJsonData = matches.map { it.value }.joinToString().split(", ")

        val temperatures = mutableMapOf<String, Temperature>()
        var minMaxTemperatures = mutableListOf<Int>() to mutableListOf<Int>()

        for (item in parsedJsonData) {
            if (item.matches(dateRegex)) {
                temperatures[item.substring(0, separatorIndex)] = Temperature(
                    minMaxTemperatures.first.minOrNull()!!,
                    minMaxTemperatures.first.maxOrNull()!!
                )
                minMaxTemperatures = mutableListOf<Int>() to mutableListOf<Int>()
            } else {
                val temp = item.split(separatorRegex)
                val min = temp[1].toDouble() - fromKelvin
                val max = temp[3].toDouble() - fromKelvin
                minMaxTemperatures.first.add(min.roundToInt())
                minMaxTemperatures.second.add(max.roundToInt())
            }
        }

        return temperatures
    }
}