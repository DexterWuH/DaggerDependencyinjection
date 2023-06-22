package com.example.daggerdependencyinjection.di.modules

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

private const val SHARED_PREFS_KEY = "UserPreference"

@Module
class SharedPrefsModule {

    @Provides
    @Singleton
    fun provideSharedPreferences(app: Application): SharedPreferences {
        return app.getSharedPreferences(SHARED_PREFS_KEY, Context.MODE_PRIVATE)
    }
}