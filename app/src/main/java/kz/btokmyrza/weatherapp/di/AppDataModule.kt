package kz.btokmyrza.weatherapp.di

import android.content.Context
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.Dispatchers
import kz.btokmyrza.weatherapp.data.location.DefaultLocationTracker
import kz.btokmyrza.weatherapp.data.mapper.CityModelMapper
import kz.btokmyrza.weatherapp.data.mapper.WeatherModelMapper
import kz.btokmyrza.weatherapp.data.network.CityAPI
import kz.btokmyrza.weatherapp.data.network.WeatherAPI
import kz.btokmyrza.weatherapp.data.preferences.DefaultUserPreferences
import kz.btokmyrza.weatherapp.data.repository.DefaultCityRepository
import kz.btokmyrza.weatherapp.data.repository.DefaultWeatherRepository
import kz.btokmyrza.weatherapp.domain.location.LocationTracker
import kz.btokmyrza.weatherapp.domain.preferences.UserPreferences
import kz.btokmyrza.weatherapp.domain.repository.CityRepository
import kz.btokmyrza.weatherapp.domain.repository.WeatherRepository
import kz.btokmyrza.weatherapp.util.Constants
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

internal val appDataModule = module {

    single<UserPreferences> {
        DefaultUserPreferences(
            sharedPreferences = androidApplication().getSharedPreferences(
                "shared_pref",
                Context.MODE_PRIVATE,
            ),
        )
    }

    single<Interceptor> {
        HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
    }
    single {
        OkHttpClient.Builder()
            .addInterceptor(interceptor = get())
            .build()
    }
    single<Converter.Factory> {
        MoshiConverterFactory.create()
    }
    single {
        Retrofit.Builder()
            .baseUrl(Constants.CITY_API_BASE_URL)
            .addConverterFactory(get())
            .client(get())
            .build()
            .create(CityAPI::class.java)
    }
    single {
        Retrofit.Builder()
            .baseUrl(Constants.WEATHER_API_BASE_URL)
            .addConverterFactory(get())
            .client(get())
            .build()
            .create(WeatherAPI::class.java)
    }


    single<LocationTracker> {
        DefaultLocationTracker(
            context = androidApplication(),
            locationClient = LocationServices.getFusedLocationProviderClient(androidApplication()),
        )
    }

    factory { CityModelMapper() }
    factory { WeatherModelMapper() }

    factory<CityRepository> {
        DefaultCityRepository(
            ioDispatcher = Dispatchers.IO,
            cityAPI = get(),
            cityModelMapper = get(),
        )
    }
    factory<WeatherRepository> {
        DefaultWeatherRepository(
            ioDispatcher = Dispatchers.IO,
            weatherAPI = get(),
            weatherModelMapper = get(),
        )
    }
}