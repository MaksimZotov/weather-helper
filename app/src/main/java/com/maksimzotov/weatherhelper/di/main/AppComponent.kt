package com.maksimzotov.weatherhelper.di.main

import android.content.Context
import com.maksimzotov.weatherhelper.data.android.AndroidSettingsRepository
import com.maksimzotov.weatherhelper.presentation.ui.cities.CitiesFragment
import com.maksimzotov.weatherhelper.presentation.ui.filter.FilterFragment
import com.maksimzotov.weatherhelper.presentation.ui.selection.SelectionFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton


@Component(modules = [AppModule::class])
@Singleton
interface AppComponent {

    val androidSettingsRepository: AndroidSettingsRepository

    fun inject(citiesFragment: CitiesFragment)
    fun inject(fragment: SelectionFragment)
    fun inject(fragment: FilterFragment)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent
    }
}

