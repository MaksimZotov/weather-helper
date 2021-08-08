package com.maksimzotov.weatherhelper.di.main

import android.content.Context
import androidx.fragment.app.Fragment
import com.maksimzotov.weatherhelper.data.android.AndroidSettingsRepository
import com.maksimzotov.weatherhelper.domain.usecases.loadcity.LoadCityUseCase
import com.maksimzotov.weatherhelper.presentation.main.activity.MainActivity
import com.maksimzotov.weatherhelper.presentation.ui.selection.SelectionFragment
import com.maksimzotov.weatherhelper.presentation.ui.selection.SelectionViewModel
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton


@Component(modules = [AppModule::class])
@Singleton
interface AppComponent {

    val androidSettingsRepository: AndroidSettingsRepository

    fun injectSelectionFragment(fragment: SelectionFragment)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent
    }
}

