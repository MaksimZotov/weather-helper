package com.maksimzotov.weatherhelper.domain.entities

data class City(
    val name: String,
    val temperatures: Map<String, Temperature>
)

