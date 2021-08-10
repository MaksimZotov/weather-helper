package com.maksimzotov.weatherhelper.presentation.main.activity

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.maksimzotov.weatherhelper.di.main.appComponent
import com.maksimzotov.weatherhelper.presentation.entities.settings.BottomNavigation
import com.maksimzotov.weatherhelper.presentation.entities.settings.DarkTheme
import com.maksimzotov.weatherhelper.presentation.entities.settings.Humidity
import com.maksimzotov.weatherhelper.presentation.entities.settings.Temperature

class SettingsSharedViewModel(application: Application): AndroidViewModel(application)  {
    private val settingsRepo = application.appComponent.androidSettingsRepository

    val bottomNavigation: LiveData<BottomNavigation?> = settingsRepo.bottomNavigation.asLiveData()
    val darkTheme: LiveData<DarkTheme?> = settingsRepo.darkTheme.asLiveData()

    val temperature: LiveData<Temperature?> = settingsRepo.temperature.asLiveData()
    val humidity: LiveData<Humidity?> = settingsRepo.humidity.asLiveData()
}