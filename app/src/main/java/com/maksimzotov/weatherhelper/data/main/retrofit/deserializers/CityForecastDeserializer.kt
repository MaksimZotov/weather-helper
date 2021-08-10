package com.maksimzotov.weatherhelper.data.main.retrofit.deserializers

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.maksimzotov.weatherhelper.domain.entities.City
import com.maksimzotov.weatherhelper.domain.entities.Date
import com.maksimzotov.weatherhelper.domain.entities.Humidity
import com.maksimzotov.weatherhelper.domain.entities.Temperature
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
        val humidityList = mutableListOf<Int>()

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
            humidityList.add(mainAsJsonObject.get("humidity").toString().toFloat().roundToInt())
        }


        var prevDate = datesString.first()

        val prevTemperatures = temperatures.first()
        var minMaxTemperatures =
            mutableListOf(prevTemperatures.first) to mutableListOf(prevTemperatures.second)

        var minHumidity = humidityList.first()
        var maxHumidity = minHumidity

        val datesForCity = mutableListOf<Date>()
        val temperaturesForCity = mutableListOf<Temperature>()
        val humidityListForCity = mutableListOf<Humidity>()

        for (i in 1 until datesString.size) {
            if (datesString[i] != prevDate) {
                val minTemperature = minMaxTemperatures.first.minOrNull()!!
                val maxTemperature = minMaxTemperatures.second.maxOrNull()!!
                datesForCity.add(createDate(prevDate))
                temperaturesForCity.add(Temperature(minTemperature, maxTemperature))
                humidityListForCity.add(Humidity(minHumidity, maxHumidity))
                minMaxTemperatures = mutableListOf<Int>() to mutableListOf<Int>()
                prevDate = datesString[i]
                minHumidity = humidityList[i]
                maxHumidity = minHumidity
            } else {
                val curTemperatures = temperatures[i]
                minMaxTemperatures.first.add(curTemperatures.first)
                minMaxTemperatures.second.add(curTemperatures.second)

                val curHumidity = humidityList[i]
                if (curHumidity < minHumidity) minHumidity = curHumidity
                if (curHumidity > maxHumidity) maxHumidity = curHumidity
            }
        }

        return City(
            name = name,
            dates = datesForCity,
            temperatures = temperaturesForCity,
            humidityList = humidityListForCity
        )
    }

    private fun createDate(dateString: String): Date {
        val list = dateString.split('-').reversed().map { it.toInt() }
        return Date(list[0], list[1], list[2])
    }
}