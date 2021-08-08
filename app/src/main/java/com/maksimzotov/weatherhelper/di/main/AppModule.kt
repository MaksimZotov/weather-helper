package com.maksimzotov.weatherhelper.di.main

import com.maksimzotov.weatherhelper.di.data.root.DataModule
import dagger.Module

@Module(includes = [
    DataModule::class
])
class AppModule