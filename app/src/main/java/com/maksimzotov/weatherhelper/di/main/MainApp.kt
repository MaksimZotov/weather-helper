package com.maksimzotov.weatherhelper.di.main

import android.app.Application
import android.content.Context

class MainApp : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .context(context = this)
            .build()
    }
}

val Context.appComponent: AppComponent
    get() = when (this) {
    is MainApp -> appComponent
    else -> applicationContext.appComponent
}