package com.maksimzotov.weatherhelper.data.main.retrofit.deserializers

import com.google.gson.*
import com.maksimzotov.weatherhelper.domain.entities.City
import com.maksimzotov.weatherhelper.domain.entities.Date
import com.maksimzotov.weatherhelper.domain.entities.Temperature
import java.lang.IllegalArgumentException
import java.lang.reflect.Type
import kotlin.math.roundToInt

class CityForecastDeserializer : JsonDeserializer<City> {

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): City {
        json
            ?: throw IllegalArgumentException("json must not be null")

        val name = json
            .asJsonObject
            .get("city")
            .asJsonObject
            .get("name")
            .toString().trim('\"')

        val list = json
            .asJsonObject
            .get("list")
            .asJsonArray

        val datesString = mutableListOf<String>()
        val temperatures = mutableListOf<Pair<Int, Int>>()

        for (item in list) {
            val itemAsJsonObject = item.asJsonObject
            datesString.add(
                itemAsJsonObject
                    .get("dt_txt")
                    .toString()
                    .trimStart('\"')
                    .substringBefore(' ')
            )

            val mainAsJsonObject = itemAsJsonObject.get("main").asJsonObject
            val minTemperature = mainAsJsonObject.get("temp_min").toString().toFloat().roundToInt()
            val maxTemperature = mainAsJsonObject.get("temp_max").toString().toFloat().roundToInt()
            temperatures.add(minTemperature to maxTemperature)
        }

        var prevDate = datesString.first()
        val prevTemperatures = temperatures.first()
        var minMaxTemperatures =
            mutableListOf(prevTemperatures.first) to mutableListOf(prevTemperatures.second)

        val datesForCity = mutableListOf<Date>()
        val temperaturesForCity = mutableListOf<Temperature>()

        for (i in 1 until datesString.size) {
            if (datesString[i] != prevDate) {
                val minTemperature = minMaxTemperatures.first.minOrNull()!!
                val maxTemperature = minMaxTemperatures.second.maxOrNull()!!
                datesForCity.add(createDate(prevDate))
                temperaturesForCity.add(Temperature(minTemperature, maxTemperature))
                minMaxTemperatures = mutableListOf<Int>() to mutableListOf<Int>()
                prevDate = datesString[i]
            } else {
                val curTemperatures = temperatures[i]
                minMaxTemperatures.first.add(curTemperatures.first)
                minMaxTemperatures.second.add(curTemperatures.second)
            }
        }

        return City(name, datesForCity, temperaturesForCity)
    }

    private fun createDate(dateString: String): Date {
        val list = dateString.split('-').reversed().map { it.toInt() }
        return Date(list[0], list[1], list[2])
    }
}