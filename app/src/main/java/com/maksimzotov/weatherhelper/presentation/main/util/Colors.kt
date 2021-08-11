package com.maksimzotov.weatherhelper.presentation.main.util

import android.graphics.Color
import android.graphics.drawable.ColorDrawable

class Colors() {
    private val colorDefaultDay = ColorDrawable(Color.parseColor("#FFFFFF"))
    private val colorOnPressedDay = ColorDrawable(Color.parseColor("#DCDCDC"))
    private val colorDefaultNight = ColorDrawable(Color.parseColor("#292929"))
    private val colorOnPressedNight = ColorDrawable(Color.parseColor("#686868"))
    private val colorGreenDay = ColorDrawable(Color.parseColor("#00DC7D"))
    private val colorGreenNight = ColorDrawable(Color.parseColor("#017845"))

    fun getStandardColor(isNightModeOn: Boolean, cityMatches: Boolean) = when {
        isNightModeOn && cityMatches -> colorGreenNight
        isNightModeOn && !cityMatches -> colorDefaultNight
        !isNightModeOn && cityMatches -> colorGreenDay
        else -> colorDefaultDay
    }

    fun getColorOnPressed(isNightModeOn: Boolean) =
        if (isNightModeOn)
            colorOnPressedNight
        else
            colorOnPressedDay
}
