package com.maksimzotov.weatherhelper.presentation.ui.filter

import androidx.lifecycle.*
import com.maksimzotov.weatherhelper.domain.entities.Date
import com.maksimzotov.weatherhelper.domain.entities.Filter
import com.maksimzotov.weatherhelper.domain.entities.Temperature
import com.maksimzotov.weatherhelper.domain.usecases.GetCurrentFilterUseCase
import com.maksimzotov.weatherhelper.domain.usecases.SetCurrentFilterUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class FilterViewModel(
    private val getCurrentFilterUseCase: GetCurrentFilterUseCase,
    private val setCurrentFilterUseCase: SetCurrentFilterUseCase
) : ViewModel() {
    val currentFilter: LiveData<Filter?> =
        getCurrentFilterUseCase.getCurrentFilter().asLiveData()

    fun setCurrentFilter() = viewModelScope.launch {
        // TODO
        setCurrentFilterUseCase.setCurrentFilter(
            Filter(
                Date(0, 0, 0),
                Date(0, 0, 0),
                Temperature(0, 0)
            )
        )
    }

    class Factory @Inject constructor(
        private val getCurrentFilterUseCase: GetCurrentFilterUseCase,
        private val setCurrentFilterUseCase: SetCurrentFilterUseCase
    ): ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return FilterViewModel(
                getCurrentFilterUseCase,
                setCurrentFilterUseCase
            ) as T
        }
    }
}