package com.maksimzotov.weatherhelper.presentation.ui.settings

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.maksimzotov.weatherhelper.data.android.AndroidSettingsDatabase
import com.maksimzotov.weatherhelper.data.android.AndroidSettingsRepository
import com.maksimzotov.weatherhelper.presentation.entities.BottomNavigation
import com.maksimzotov.weatherhelper.presentation.entities.DarkTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SettingsViewModel(application: Application): AndroidViewModel(application) {
    private val settingsRepo = AndroidSettingsRepository(
        AndroidSettingsDatabase.getDatabase(application).androidSettingsDao()
    )

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