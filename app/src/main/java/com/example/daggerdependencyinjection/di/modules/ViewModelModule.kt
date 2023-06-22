package com.example.daggerdependencyinjection.di.modules

import androidx.lifecycle.ViewModel
import com.example.daggerdependencyinjection.di.ViewModelKey
import com.example.daggerdependencyinjection.ui.forecast.ForecastViewModel
import com.example.daggerdependencyinjection.ui.settings.SettingsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ForecastViewModel::class)
    abstract fun bindForecastViewModel(viewModel: ForecastViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SettingsViewModel::class)
    abstract fun bindSettingsViewModel(viewModel: SettingsViewModel): ViewModel
}