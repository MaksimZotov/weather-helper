package com.maksimzotov.weatherhelper.domain.entities

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.maksimzotov.weatherhelper.data.main.room.domain.converters.CityConverter
import com.maksimzotov.weatherhelper.data.main.room.domain.MainTableNames
import java.io.Serializable

@Entity(tableName = MainTableNames.CITIES)
@TypeConverters(CityConverter::class)
data class City(
    @PrimaryKey var name: String,
    val dates: List<Date>,
    val temperatures: List<Temperature>,
    val humidityList: List<Humidity>,
    val lastUpdate: String
) : Serializable {
    @Ignore private var _isMatchesToFilter = false
    val isMatchesToFilter get () = _isMatchesToFilter

    fun checkMatchingToFilter(filter: Filter) {
        if (filter.startDate < dates.first() || filter.endDate > dates.last()) {
            _isMatchesToFilter = false
            return
        }
        val indices = dates.mapIndexed() { index, date ->
            if (date >= filter.startDate && date <= filter.endDate) {
                return@mapIndexed index
            } else {
                return@mapIndexed -1
            }
        }.filter { it != -1 }

        _isMatchesToFilter = true
        for (i in indices) {
            val temperature = temperatures[i]
            val temperatureMatches =
                temperature.min >= filter.temperature.min &&
                temperature.max <= filter.temperature.max

            val humidity = humidityList[i]
            val humidityMatches =
                humidity.min >= filter.humidity.min &&
                humidity.max <= filter.humidity.max

            _isMatchesToFilter = isMatchesToFilter && temperatureMatches && humidityMatches

            if (!isMatchesToFilter) {
                break
            }
        }
    }
}