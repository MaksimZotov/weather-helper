package com.maksimzotov.weatherhelper.presentation.main.activity

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.maksimzotov.weatherhelper.data.android.AndroidSettingsDatabase
import com.maksimzotov.weatherhelper.data.android.AndroidSettingsRepository
import com.maksimzotov.weatherhelper.data.android.BottomNavigation
import com.maksimzotov.weatherhelper.data.android.DarkTheme

class MainActivityViewModel(application: Application): AndroidViewModel(application)  {
    private val settingsRepo = AndroidSettingsRepository(
        AndroidSettingsDatabase.getDatabase(application).androidSettingsDao()
    )

    val bottomNavigation: LiveData<BottomNavigation?> = settingsRepo.bottomNavigation.asLiveData()
    val darkTheme: LiveData<DarkTheme?> = settingsRepo.darkTheme.asLiveData()
}