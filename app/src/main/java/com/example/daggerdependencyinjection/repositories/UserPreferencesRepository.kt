package com.example.daggerdependencyinjection.repositories

import android.annotation.SuppressLint
import android.content.SharedPreferences
import javax.inject.Inject

private const val LATITUDE_KEY = "PreferredLatitude"
private const val LONGITUDE_KEY = "PreferredLongitude"

typealias Coordinates = Pair<String, String>

class UserPreferencesRepository @Inject constructor(private val sharedPrefs: SharedPreferences) :
    UserPreferencesRepositoryContract {

    override val defaultLatitude: String = "33.7488"
    override val defaultLongitude: String = "84.3877"

    override fun getUserPreferredLatitudeLongitude(): Coordinates? {
        val latitude = sharedPrefs.getString(LATITUDE_KEY, defaultLatitude) ?: return null
        val longitude = sharedPrefs.getString(LONGITUDE_KEY, defaultLongitude) ?: return null
        return Coordinates(latitude, longitude)
    }

    @SuppressLint("ApplySharedPref")
    override suspend fun setUserPreferredLatitudeLongitude(coordinates: Coordinates) {
        sharedPrefs.edit().putString(LATITUDE_KEY, coordinates.first).commit()
        sharedPrefs.edit().putString(LONGITUDE_KEY, coordinates.second).commit()
    }
}

interface UserPreferencesRepositoryContract {
    val defaultLatitude: String
    val defaultLongitude: String
    fun getUserPreferredLatitudeLongitude(): Coordinates?
    suspend fun setUserPreferredLatitudeLongitude(coordinates: Coordinates)
}