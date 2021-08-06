package com.maksimzotov.weatherhelper.di.data

import com.maksimzotov.weatherhelper.di.data.android.DataAndroidModule
import dagger.Module

@Module(includes = [DataAndroidModule::class])
class DataModule