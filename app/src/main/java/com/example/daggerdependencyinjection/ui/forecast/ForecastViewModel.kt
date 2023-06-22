package com.example.daggerdependencyinjection.ui.forecast

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.daggerdependencyinjection.data.DayForecast
import com.example.daggerdependencyinjection.repositories.ForecastRepositoryContract
import com.example.daggerdependencyinjection.repositories.UserPreferencesRepositoryContract
import kotlinx.coroutines.launch
import javax.inject.Inject

class ForecastViewModel @Inject constructor(
    private val repository: ForecastRepositoryContract,
    private val userPreferencesRepository: UserPreferencesRepositoryContract
) : ViewModel() {

    val forecastLiveData: MutableLiveData<List<DayForecast>> = MutableLiveData(emptyList())

    fun getForecast() {
        val coordinates = userPreferencesRepository.getUserPreferredLatitudeLongitude()
        coordinates ?: return
        viewModelScope.launch {
            val latitude = coordinates.first.toDouble()
            val longitude = coordinates.second.toDouble()
            val forecast = repository.getFiveDayForecast(latitude, longitude)
            forecastLiveData.postValue(forecast)
        }
    }
}