package com.maksimzotov.weatherhelper.di.main

import com.maksimzotov.weatherhelper.di.data.root.DataModule
import com.maksimzotov.weatherhelper.di.presentation.root.PresentationModule
import dagger.Module

@Module(includes = [
    DataModule::class,
    PresentationModule::class
])
class AppModule