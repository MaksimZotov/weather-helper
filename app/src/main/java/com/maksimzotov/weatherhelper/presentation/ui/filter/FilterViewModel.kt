package com.maksimzotov.weatherhelper.presentation.ui.filter

import androidx.lifecycle.*
import com.maksimzotov.weatherhelper.domain.entities.*
import com.maksimzotov.weatherhelper.domain.entities.Date
import com.maksimzotov.weatherhelper.domain.usecases.GetCurrentFilterUseCase
import com.maksimzotov.weatherhelper.domain.usecases.SetCurrentFilterUseCase
import com.maksimzotov.weatherhelper.presentation.entities.filters.Preferences
import com.maksimzotov.weatherhelper.presentation.main.util.DateConverter
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch
import java.util.*

class FilterViewModel(
    private val preference: Preferences,
    private val getCurrentFilterUseCase: GetCurrentFilterUseCase,
    private val setCurrentFilterUseCase: SetCurrentFilterUseCase
) : ViewModel() {
    private val dateConverter = DateConverter()
    private val dateFormat = dateConverter.dateFormat
    private val defaultDateStr = dateFormat.format(Calendar.getInstance().time)
    private val defaultDate = createDate(defaultDateStr)
    private val defaultRangeTemperature = 15 to 35
    private val defaultRangeHumidity = 25 to 75

    val firstDate = MutableLiveData(defaultDateStr)
    val lastDate = MutableLiveData(defaultDateStr)
    val rangeTemperature = MutableLiveData(defaultRangeTemperature)
    val rangeHumidity = MutableLiveData(defaultRangeHumidity)

    val filter: LiveData<Filter?> = when (preference) {
        Preferences.CURRENT_FILTER -> getCurrentFilterUseCase.getCurrentFilter().asLiveData()
        Preferences.DRY_CLIMATE -> MutableLiveData(
            DefaultFilters.getFiltersForDryClimate(defaultDate, defaultDate)
        )
        Preferences.TEMPERATE_CLIMATE -> MutableLiveData(
            DefaultFilters.getFilterForTemperateClimate(defaultDate, defaultDate)
        )
    }

    val popBackstack = MutableLiveData(false)

    private var _flagSetCurrentFilter = true
    val flagSetCurrentFilter: Boolean get() {
        val prev = _flagSetCurrentFilter
        _flagSetCurrentFilter = false
        return prev
    }

    fun setCurrentFilter() {
        val firstDate = firstDate.value ?: return
        val lastDate = lastDate.value ?: return
        val rangeTemperature = rangeTemperature.value ?: return
        val rangeHumidity = rangeHumidity.value ?: return

        viewModelScope.launch {
            setCurrentFilterUseCase.setCurrentFilter(
                Filter(
                    startDate = createDate(firstDate),
                    endDate = createDate(lastDate),
                    temperature = Temperature(rangeTemperature.first, rangeTemperature.second),
                    humidity = Humidity(rangeHumidity.first, rangeHumidity.second)
                )
            )
            popBackstack.value = true
        }
    }

    class Factory @AssistedInject constructor(
        @Assisted("preference") private val preference: Preferences,
        private val getCurrentFilterUseCase: GetCurrentFilterUseCase,
        private val setCurrentFilterUseCase: SetCurrentFilterUseCase
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return FilterViewModel(
                preference,
                getCurrentFilterUseCase,
                setCurrentFilterUseCase
            ) as T
        }

        @AssistedFactory
        interface FactoryForVMFactory {
            fun create(
                @Assisted("preference") preference: Preferences
            ): FilterViewModel.Factory
        }
    }

    private fun createDate(dateString: String): Date {
        val date = dateConverter.fromCharSequenceToList(dateString)
        return Date(date[0], date[1], date[2])
    }
}