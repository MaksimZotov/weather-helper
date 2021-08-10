package com.maksimzotov.weatherhelper.di.data

import android.content.Context
import androidx.room.Room
import com.maksimzotov.weatherhelper.data.main.RepositoryImpl
import com.maksimzotov.weatherhelper.data.main.room.MainDao
import com.maksimzotov.weatherhelper.data.main.room.MainDatabase
import com.maksimzotov.weatherhelper.data.main.room.RoomConstants
import com.maksimzotov.weatherhelper.domain.Repository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataMainModule {

    @Provides
    @Singleton
    fun provideRepository(
        mainDao: MainDao
    ): Repository {
        return RepositoryImpl(mainDao)
    }

    @Provides
    @Singleton
    fun provideMainDao(
        mainDatabase: MainDatabase
    ): MainDao {
        return mainDatabase.mainDao()
    }

    @Provides
    @Singleton
    fun provideMainDatabase(context: Context): MainDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            MainDatabase::class.java,
            RoomConstants.DATABASE_NAME
        ).build()
    }
}