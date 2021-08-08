package com.maksimzotov.weatherhelper.domain.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = MainTableNames.CURRENT_FILTER)
data class Filter(
    @Embedded(prefix = "startDate") var startDate: Date,
    @Embedded(prefix = "endDate") var endDate: Date,
    @Embedded var temperature: Temperature
) {
    @PrimaryKey var id = 0
}
