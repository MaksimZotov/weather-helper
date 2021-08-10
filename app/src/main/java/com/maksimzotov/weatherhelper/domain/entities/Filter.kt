package com.maksimzotov.weatherhelper.domain.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.maksimzotov.weatherhelper.data.main.room.domain.MainTableNames


@Entity(tableName = MainTableNames.CURRENT_FILTER)
data class Filter(
    @Embedded(prefix = "startDate") var startDate: Date,
    @Embedded(prefix = "endDate") var endDate: Date,
    @Embedded(prefix = "temperature") var temperature: Temperature,
    @Embedded(prefix = "humidity") var humidity: Humidity
) {
    @PrimaryKey var id = 0
}
