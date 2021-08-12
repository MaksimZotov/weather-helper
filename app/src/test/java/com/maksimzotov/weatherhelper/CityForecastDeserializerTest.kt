package com.maksimzotov.weatherhelper

import com.google.gson.JsonElement
import com.maksimzotov.weatherhelper.data.main.retrofit.deserializers.CityForecastDeserializer
import org.junit.Assert.assertThrows
import org.junit.Test

class CityForecastDeserializerTest {
    private val cityDeserializer = CityForecastDeserializer()
    private val incorrectJsonElement: JsonElement? = null

    @Test
    fun testDeserialize() {
        assertThrows(IllegalArgumentException::class.java) {
            cityDeserializer.deserialize(incorrectJsonElement, null, null)
        }
    }
}