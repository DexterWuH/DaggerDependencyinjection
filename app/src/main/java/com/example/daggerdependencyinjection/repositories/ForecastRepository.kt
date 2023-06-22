package com.example.daggerdependencyinjection.repositories

import com.example.daggerdependencyinjection.data.DayForecast
import com.example.daggerdependencyinjection.network.ForecastApi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ForecastRepository @Inject constructor(
    private val api: ForecastApi,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ForecastRepositoryContract {

    override suspend fun getFiveDayForecast(lat: Double, lon: Double): List<DayForecast> {
        return withContext(dispatcher) {
            val response = api.getFiveDayForecast(lat, lon)
            return@withContext response.forecasts
        }
    }
}

interface ForecastRepositoryContract {
    suspend fun getFiveDayForecast(lat: Double, lon: Double): List<DayForecast>
}