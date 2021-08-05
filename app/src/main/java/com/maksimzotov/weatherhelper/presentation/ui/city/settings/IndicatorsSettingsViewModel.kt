package com.maksimzotov.weatherhelper.presentation.ui.city.settings

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.maksimzotov.weatherhelper.data.android.AndroidSettingsDatabase
import com.maksimzotov.weatherhelper.data.android.AndroidSettingsRepository
import com.maksimzotov.weatherhelper.presentation.entities.Temperature
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class IndicatorsSettingsViewModel(application: Application): AndroidViewModel(application) {
    private val settingsRepo = AndroidSettingsRepository(
        AndroidSettingsDatabase.getDatabase(application).androidSettingsDao()
    )

    fun showTemperature() = viewModelScope.launch(Dispatchers.IO) {
        settingsRepo.setTemperature(Temperature(true))
    }

    fun hideTemperature() = viewModelScope.launch(Dispatchers.IO) {
        settingsRepo.setTemperature(Temperature(false))
    }
}