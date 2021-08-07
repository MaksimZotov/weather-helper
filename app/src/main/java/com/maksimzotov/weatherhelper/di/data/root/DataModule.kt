package com.maksimzotov.weatherhelper.di.data.root

import com.maksimzotov.weatherhelper.di.data.AndroidModule
import com.maksimzotov.weatherhelper.di.data.LogicModule
import dagger.Module

@Module(includes = [
    AndroidModule::class,
    LogicModule::class
])
class DataModule