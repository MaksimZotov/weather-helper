package com.maksimzotov.weatherhelper.domain.entities

data class Date(
    val day: Int = 0,
    val month: Int = 0,
    val year: Int = 0
) {
    override fun toString(): String {
        return String.format("%02d.%02d.%d", day, month, year)
    }
}