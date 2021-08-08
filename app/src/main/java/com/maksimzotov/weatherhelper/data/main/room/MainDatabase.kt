package com.maksimzotov.weatherhelper.data.main.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.maksimzotov.weatherhelper.domain.entities.Filter

@Database(
    entities = [
        Filter::class
    ],
    version = 1
)
abstract class MainDatabase : RoomDatabase() {
    abstract fun mainDao(): MainDao
}