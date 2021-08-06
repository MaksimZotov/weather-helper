package com.maksimzotov.weatherhelper.domain.entities.cities

import com.maksimzotov.weatherhelper.domain.entities.indicators.Temperature


data class City(val name: String, val temperatures: Map<String, Temperature>)

