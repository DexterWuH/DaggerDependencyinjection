package com.example.daggerdependencyinjection.di.modules

import com.example.daggerdependencyinjection.ui.forecast.ForecastFragment
import com.example.daggerdependencyinjection.ui.settings.SettingsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilderModule {

    @ContributesAndroidInjector(modules = [ViewModelModule::class])
    abstract fun contributeForecastFragment(): ForecastFragment

    @ContributesAndroidInjector(modules = [ViewModelModule::class])
    abstract fun contributeSettingsFragment(): SettingsFragment
}