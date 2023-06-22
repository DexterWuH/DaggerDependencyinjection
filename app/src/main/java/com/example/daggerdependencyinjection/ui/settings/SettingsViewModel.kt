package com.example.daggerdependencyinjection.ui.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.daggerdependencyinjection.repositories.Coordinates
import com.example.daggerdependencyinjection.repositories.UserPreferencesRepositoryContract
import kotlinx.coroutines.launch
import javax.inject.Inject

class SettingsViewModel @Inject constructor(
    private val repository: UserPreferencesRepositoryContract
) : ViewModel() {

    fun setUserPreferredCoordinates(coordinates: Coordinates) {
        viewModelScope.launch {
            repository.setUserPreferredLatitudeLongitude(coordinates)
        }
    }
}