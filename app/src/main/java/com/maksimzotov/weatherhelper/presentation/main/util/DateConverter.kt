package com.maksimzotov.weatherhelper.presentation.main.util

import android.annotation.SuppressLint
import java.text.SimpleDateFormat

class DateConverter {
    @SuppressLint("SimpleDateFormat")
    val dateFormat = SimpleDateFormat("dd.MM.yyyy")

    fun fromIntsToString(dayOfMonth: Int, month: Int, year: Int): String =
        String.format("%02d.%02d.%d", dayOfMonth, month, year)

    fun fromStringToList(date: String): List<Int> =
        date.split('.').map { it.toInt() }
}