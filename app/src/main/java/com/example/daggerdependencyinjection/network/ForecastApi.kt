package com.example.daggerdependencyinjection.network

import com.example.daggerdependencyinjection.data.WeatherForecastResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ForecastApi {
    @GET("forecast")
    suspend fun getFiveDayForecast(
        @Query("lat")lat: Double,
        @Query("lon")lon: Double,
    ): WeatherForecastResponse
}
