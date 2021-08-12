package com.maksimzotov.weatherhelper.di.data

import android.content.Context
import androidx.room.Room
import com.google.gson.GsonBuilder
import com.maksimzotov.weatherhelper.data.main.RepositoryImpl
import com.maksimzotov.weatherhelper.data.main.retrofit.RetrofitConstants
import com.maksimzotov.weatherhelper.data.main.retrofit.WeatherApi
import com.maksimzotov.weatherhelper.data.main.retrofit.deserializers.CityForecastDeserializer
import com.maksimzotov.weatherhelper.data.main.room.MainDao
import com.maksimzotov.weatherhelper.data.main.room.MainDatabase
import com.maksimzotov.weatherhelper.data.main.room.RoomConstants
import com.maksimzotov.weatherhelper.domain.Repository
import com.maksimzotov.weatherhelper.domain.entities.City
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class DataMainModule {

    @Provides
    @Singleton
    fun provideRepository(
        mainDao: MainDao,
        weatherApi: WeatherApi
    ): Repository {
        return RepositoryImpl(mainDao, weatherApi)
    }

    @Provides
    @Singleton
    fun provideMainDao(
        mainDatabase: MainDatabase
    ): MainDao {
        return mainDatabase.mainDao()
    }

    @Provides
    @Singleton
    fun provideMainDatabase(context: Context): MainDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            MainDatabase::class.java,
            RoomConstants.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideWeatherApi(
        retrofit: Retrofit
    ): WeatherApi {
        return retrofit.create(WeatherApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        cityForecastDeserializer: CityForecastDeserializer
    ): Retrofit {
        val gsonBuilder = GsonBuilder()
        gsonBuilder.registerTypeAdapter(City::class.java, cityForecastDeserializer)
        val myGson = gsonBuilder.create()
        val converterFactory = GsonConverterFactory.create(myGson)

        return Retrofit.Builder()
            .baseUrl(RetrofitConstants.BASE_URL)
            .addConverterFactory(converterFactory)
            .build()
    }
}