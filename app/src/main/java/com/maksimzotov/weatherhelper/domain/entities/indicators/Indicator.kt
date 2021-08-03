package com.maksimzotov.weatherhelper.domain.entities.indicators

sealed class Indicator(val name: String, val min: Int, val max: Int)

class Temperature(min: Int, max: Int) : Indicator("Temperature", min, max)