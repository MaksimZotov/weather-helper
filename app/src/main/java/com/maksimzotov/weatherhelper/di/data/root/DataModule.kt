package com.maksimzotov.weatherhelper.di.data.root

import com.maksimzotov.weatherhelper.di.data.DataAndroidModule
import com.maksimzotov.weatherhelper.di.data.DataMainModule
import dagger.Module

@Module(includes = [
    DataAndroidModule::class,
    DataMainModule::class
])
class DataModule