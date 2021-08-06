package com.maksimzotov.weatherhelper.di.data

import com.maksimzotov.weatherhelper.di.data.android.DataAndroidModule
import com.maksimzotov.weatherhelper.di.data.logic.DataLogicModule
import dagger.Module

@Module(includes = [
    DataAndroidModule::class,
    DataLogicModule::class
])
class DataModule