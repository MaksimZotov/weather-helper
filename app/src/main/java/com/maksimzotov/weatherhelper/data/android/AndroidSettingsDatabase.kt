package com.maksimzotov.weatherhelper.data.android

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.maksimzotov.weatherhelper.presentation.entities.BottomNavigation
import com.maksimzotov.weatherhelper.presentation.entities.DarkTheme
import com.maksimzotov.weatherhelper.presentation.entities.Temperature

@Database(
    entities = [
        BottomNavigation::class,
        DarkTheme::class,
        Temperature::class
    ],
    version = 1
)
abstract class AndroidSettingsDatabase : RoomDatabase() {

    abstract fun androidSettingsDao(): AndroidSettingsDao

    companion object {

        val DATABASE = "database"

        @Volatile
        private var INSTANCE: AndroidSettingsDatabase? = null

        fun getDatabase(context: Context) : AndroidSettingsDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AndroidSettingsDatabase::class.java,
                    DATABASE
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}