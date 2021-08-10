package com.maksimzotov.weatherhelper.di.main

import com.maksimzotov.weatherhelper.di.data.root.DataModule
import com.maksimzotov.weatherhelper.di.domain.DomainModule
import dagger.Module

@Module(includes = [
    DataModule::class,
    DomainModule::class
])
class AppModule