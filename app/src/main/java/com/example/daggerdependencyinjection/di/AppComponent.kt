package com.example.daggerdependencyinjection.di

import android.app.Application
import com.example.daggerdependencyinjection.WeatherApplication
import com.example.daggerdependencyinjection.di.modules.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        SharedPrefsModule::class,
        NetworkModule::class,
        RepositoryModule::class,
        ViewModelFactoryModule::class,
        ViewModelModule::class,
        FragmentBuilderModule::class,
        ActivityBuilderModule::class
    ]
)

interface AppComponent : AndroidInjector<WeatherApplication> {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): AppComponent
    }
}