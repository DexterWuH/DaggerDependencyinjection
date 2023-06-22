package com.example.daggerdependencyinjection.data

import com.google.gson.annotations.SerializedName

data class WeatherForecastResponse(
    @SerializedName("cnt")
    val numberOfResults: Int,
    @SerializedName("list")
    val forecasts: List<DayForecast>
)

data class DayForecast(
    @SerializedName("dt")
    val dayTime: Long,
    @SerializedName("main")
    val dayConditions: DayConditions,
    @SerializedName("weather")
    val descriptions: List<WeatherDescription>,
    @SerializedName("pop")
    val probabilityOfRain: Double
)

data class DayConditions(
    @SerializedName("temp")
    val averageTemperature: Double,
    @SerializedName("feels_like")
    val averageTemperatureFeeling: Double,
    @SerializedName("temp_min")
    val minTemperature: Double,
    @SerializedName("temp_max")
    val maxTemperature: Double,
    @SerializedName("humidity")
    val humidityPercentage: Int
)

data class WeatherDescription(
    @SerializedName("main")
    val overallWeatherCondition: String,
    @SerializedName("description")
    val detailedDescription: String,
)