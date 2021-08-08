package com.maksimzotov.weatherhelper.domain.entities

data class Date(
    val day: Int,
    val month: Int,
    val year: Int
) {
    override fun toString(): String {
        return String.format("%02d.%02d.%d", day, month, year)
    }
}