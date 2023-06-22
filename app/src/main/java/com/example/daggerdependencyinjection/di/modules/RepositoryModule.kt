package com.example.daggerdependencyinjection.di.modules

import android.content.SharedPreferences
import com.example.daggerdependencyinjection.network.ForecastApi
import com.example.daggerdependencyinjection.repositories.ForecastRepository
import com.example.daggerdependencyinjection.repositories.ForecastRepositoryContract
import com.example.daggerdependencyinjection.repositories.UserPreferencesRepository
import com.example.daggerdependencyinjection.repositories.UserPreferencesRepositoryContract
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideForecastRepository(api: ForecastApi): ForecastRepositoryContract {
        return ForecastRepository(api)
    }

    @Provides
    @Singleton
    fun provideSettingsRepository(sharedPrefs: SharedPreferences): UserPreferencesRepositoryContract {
        return UserPreferencesRepository(sharedPrefs)
    }
}