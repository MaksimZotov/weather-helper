package com.maksimzotov.weatherhelper.di.domain

import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Qualifier

@Module
class DomainModule {

    @Provides
    fun provideCoroutineDispatcherForIO(): CoroutineDispatcher {
        return Dispatchers.IO
    }

    @Provides
    @ForHeavyCalculations
    fun provideCoroutineDispatcherForHeavyCalculations(): CoroutineDispatcher {
        return Dispatchers.Default
    }
}

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class ForHeavyCalculations