package com.maksimzotov.weatherhelper.domain.entities


object DefaultFilters {
    fun getFiltersForDryClimate(startDate: Date, endDate: Date) = Filter(
        startDate = startDate,
        endDate = endDate,
        temperature = Temperature(20, 30),
        humidity = Humidity(30, 60)
    )

    fun getFilterForTemperateClimate(startDate: Date, endDate: Date) = Filter(
        startDate = startDate,
        endDate = endDate,
        temperature = Temperature(15, 25),
        humidity = Humidity(50, 80)
    )
}