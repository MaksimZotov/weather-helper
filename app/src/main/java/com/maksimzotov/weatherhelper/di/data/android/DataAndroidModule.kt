package com.maksimzotov.weatherhelper.di.data.android

import android.content.Context
import androidx.room.Room
import com.maksimzotov.weatherhelper.data.android.AndroidSettingsDao
import com.maksimzotov.weatherhelper.data.android.AndroidSettingsDatabase
import com.maksimzotov.weatherhelper.data.android.AndroidSettingsRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object DataAndroidModule {

    @Provides
    @Singleton
    fun provideAndroidSettingsRepository(
        androidSettingsDao: AndroidSettingsDao
    ): AndroidSettingsRepository {
        return AndroidSettingsRepository(
            androidSettingsDao = androidSettingsDao
        )
    }

    @Provides
    @Singleton
    fun provideAndroidSettingsDao(
        androidSettingsDatabase: AndroidSettingsDatabase
    ): AndroidSettingsDao {
        return androidSettingsDatabase.androidSettingsDao()
    }

    @Provides
    @Singleton
    fun provideAndroidSettingsDatabase(context: Context): AndroidSettingsDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AndroidSettingsDatabase::class.java,
            "database"
        ).build()
    }
}