package com.maksimzotov.weatherhelper.presentation.ui.settings.indicators

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.maksimzotov.weatherhelper.di.main.appComponent
import com.maksimzotov.weatherhelper.presentation.entities.settings.HumidityToShow
import com.maksimzotov.weatherhelper.presentation.entities.settings.TemperatureToShow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class IndicatorsSettingsViewModel(application: Application): AndroidViewModel(application) {
    private val settingsRepo = application.appComponent.androidSettingsRepository

    fun showTemperature() = viewModelScope.launch(Dispatchers.IO) {
        settingsRepo.setTemperatureToShow(TemperatureToShow(true))
    }

    fun hideTemperature() = viewModelScope.launch(Dispatchers.IO) {
        settingsRepo.setTemperatureToShow(TemperatureToShow(false))
    }

    fun showHumidity() = viewModelScope.launch(Dispatchers.IO) {
        settingsRepo.setHumidityToShow(HumidityToShow(true))
    }

    fun hideHumidity() = viewModelScope.launch(Dispatchers.IO) {
        settingsRepo.setHumidityToShow(HumidityToShow(false))
    }
}