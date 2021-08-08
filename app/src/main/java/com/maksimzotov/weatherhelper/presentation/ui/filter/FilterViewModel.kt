package com.maksimzotov.weatherhelper.presentation.ui.filter

import androidx.lifecycle.*
import com.maksimzotov.weatherhelper.domain.entities.Date
import com.maksimzotov.weatherhelper.domain.entities.Filter
import com.maksimzotov.weatherhelper.domain.entities.Temperature
import com.maksimzotov.weatherhelper.domain.usecases.GetCurrentFilterUseCase
import com.maksimzotov.weatherhelper.domain.usecases.SetCurrentFilterUseCase
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

class FilterViewModel(
    private val getCurrentFilterUseCase: GetCurrentFilterUseCase,
    private val setCurrentFilterUseCase: SetCurrentFilterUseCase
) : ViewModel() {
    private val dateConverter = DateConverter()
    private val dateFormat = dateConverter.dateFormat
    private val defaultDate = dateFormat.format(Calendar.getInstance().time)

    val firstDate: MutableLiveData<String> = MutableLiveData(defaultDate)
    val lastDate: MutableLiveData<String> = MutableLiveData(defaultDate)
    val rangeTemperature: MutableLiveData<Pair<Int, Int>> = MutableLiveData(15 to 35)

    val currentFilter: LiveData<Filter?> =
        getCurrentFilterUseCase.getCurrentFilter().asLiveData()

    fun setCurrentFilter() = viewModelScope.launch {
        val firstDate = firstDate.value?.let { dateConverter.fromStringToList(it) } ?: return@launch
        val lastDate = lastDate.value?.let { dateConverter.fromStringToList(it) } ?: return@launch
        val rangeTemperature = rangeTemperature.value ?: return@launch

        setCurrentFilterUseCase.setCurrentFilter(
            Filter(
                Date(firstDate[0], firstDate[1], firstDate[2]),
                Date(lastDate[0], lastDate[1], lastDate[2]),
                Temperature(rangeTemperature.first, rangeTemperature.second)
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