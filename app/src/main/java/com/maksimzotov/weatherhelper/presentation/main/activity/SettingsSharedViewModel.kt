package com.maksimzotov.weatherhelper.presentation.main.activity

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.maksimzotov.weatherhelper.data.android.AndroidSettingsDatabase
import com.maksimzotov.weatherhelper.data.android.AndroidSettingsRepository
import com.maksimzotov.weatherhelper.di.main.appComponent
import com.maksimzotov.weatherhelper.presentation.entities.BottomNavigation
import com.maksimzotov.weatherhelper.presentation.entities.DarkTheme
import com.maksimzotov.weatherhelper.presentation.entities.Temperature

class SettingsSharedViewModel(application: Application): AndroidViewModel(application)  {
    private val settingsRepo = application.appComponent.androidSettingsRepository

    val bottomNavigation: LiveData<BottomNavigation?> = settingsRepo.bottomNavigation.asLiveData()
    val darkTheme: LiveData<DarkTheme?> = settingsRepo.darkTheme.asLiveData()

    val temperature: LiveData<Temperature?> = settingsRepo.temperature.asLiveData()
}