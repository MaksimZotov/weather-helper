package com.maksimzotov.weatherhelper.domain.entities


object DefaultFilters {
    fun getFiltersForDryClimate(startDate: Date, endDate: Date) =
        Filter(startDate, endDate, Temperature(20, 30))

    fun getFilterForTemperateClimate(startDate: Date, endDate: Date) =
        Filter(startDate, endDate, Temperature(15, 25))
}