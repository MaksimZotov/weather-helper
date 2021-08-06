package com.maksimzotov.weatherhelper.data.logic.loadcity

import com.maksimzotov.weatherhelper.domain.entities.cities.City
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("forecast?")
    suspend fun getCity(
        @Query("q") name: String,
        @Query("appid") key: String = "76213d5339b06a994535d0cfcdbeab22"
    ): Response<City>
}