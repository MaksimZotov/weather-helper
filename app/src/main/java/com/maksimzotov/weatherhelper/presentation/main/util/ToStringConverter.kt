package com.maksimzotov.weatherhelper.presentation.main.util

class ToStringConverter {
    fun convertTemperature(value: Int): String = when {
        value > 0 -> "+$value"
        else -> value.toString()
    }

    fun convertHumidity(value: Int): String =
        "$value%"
}