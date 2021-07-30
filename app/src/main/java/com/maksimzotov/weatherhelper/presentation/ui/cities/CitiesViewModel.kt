package com.maksimzotov.weatherhelper.presentation.ui.cities

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CitiesViewModel : ViewModel() {
    private val _checkDataBindingWithInclude = MutableLiveData(false)
    val checkDataBindingWithInclude: LiveData<Boolean> = _checkDataBindingWithInclude

    fun setTrue() {
        _checkDataBindingWithInclude.value = true
    }
}