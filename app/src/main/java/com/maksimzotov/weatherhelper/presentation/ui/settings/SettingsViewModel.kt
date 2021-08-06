package com.maksimzotov.weatherhelper.presentation.ui.settings

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.maksimzotov.weatherhelper.di.main.appComponent
import com.maksimzotov.weatherhelper.presentation.entities.BottomNavigation
import com.maksimzotov.weatherhelper.presentation.entities.DarkTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SettingsViewModel(application: Application): AndroidViewModel(application) {
    private val settingsRepo = application.appComponent.androidSettingsRepository

    fun showBottomNavigation() = viewModelScope.launch(Dispatchers.IO) {
        settingsRepo.setBottomNavigation(BottomNavigation(true))
    }

    fun hideBottomNavigation() = viewModelScope.launch(Dispatchers.IO) {
        settingsRepo.setBottomNavigation(BottomNavigation(false))
    }

    fun switchToDarkTheme() = viewModelScope.launch(Dispatchers.IO) {
        settingsRepo.setDarkTheme(DarkTheme(true))
    }

    fun switchToLightTheme() = viewModelScope.launch(Dispatchers.IO) {
        settingsRepo.setDarkTheme(DarkTheme(false))
    }
}