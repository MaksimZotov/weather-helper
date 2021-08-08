package com.maksimzotov.weatherhelper.data.logic.loadcity

import com.google.gson.GsonBuilder
import com.maksimzotov.weatherhelper.domain.entities.City
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitInstance {
    private const val BASE_URL = "https://api.openweathermap.org/data/2.5/"

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(buildGsonConverter())
            .build()
    }

    val weatherApi by lazy {
        retrofit.create(WeatherApi::class.java)
    }

    private fun buildGsonConverter(): GsonConverterFactory {
        val gsonBuilder = GsonBuilder()
        gsonBuilder.registerTypeAdapter(City::class.java, CityDeserializer())
        val myGson = gsonBuilder.create()
        return GsonConverterFactory.create(myGson)
    }
}