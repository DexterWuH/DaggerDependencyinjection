package com.example.daggerdependencyinjection.di.modules

import com.example.daggerdependencyinjection.BuildConfig
import com.example.daggerdependencyinjection.network.ForecastApi
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkModule {

    companion object {
        private const val FORECAST_BASE_URL = "https://api.openweathermap.org/data/2.5/"
        private const val API_KEY = "appid"
    }

    @Provides
    @Singleton
    @Named("KeyInterceptor")
    fun provideApiKeyInterceptor(): Interceptor = Interceptor { chain ->
        val request = chain.request()
        val url = request.url.newBuilder().addQueryParameter(API_KEY, BuildConfig.API_KEY).build()
        chain.proceed(request.newBuilder().url(url).build())
    }

    @Provides
    @Singleton
    @Named("LogInterceptor")
    fun provideLogInterceptor(): Interceptor = HttpLoggingInterceptor().apply {
        if (BuildConfig.DEBUG) {
            apply { level = HttpLoggingInterceptor.Level.BODY }
        } else {
            apply { level = HttpLoggingInterceptor.Level.NONE }
        }
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        @Named("KeyInterceptor") keyInterceptor: Interceptor,
        @Named("LogInterceptor") logInterceptor: Interceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(keyInterceptor)
            .addInterceptor(logInterceptor)
            .connectTimeout(5, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(FORECAST_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideForecastApi(retrofit: Retrofit): ForecastApi {
        return retrofit.create(ForecastApi::class.java)
    }
}