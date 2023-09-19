package kz.btokmyrza.weatherapp.di

import kz.btokmyrza.weatherapp.presentation.feature.city.search.CitySearchViewModel
import kz.btokmyrza.weatherapp.presentation.feature.weather.WeatherViewModel
import kz.btokmyrza.weatherapp.presentation.feature.welcome.WelcomeViewModel
import kz.btokmyrza.weatherapp.presentation.mapper.CityDvoMapper
import kz.btokmyrza.weatherapp.presentation.mapper.WeatherDvoMapper
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val appPresentationModule = module {

    factory { CityDvoMapper() }
    factory { WeatherDvoMapper() }

    viewModel {
        WelcomeViewModel(
            locationTracker = get(),
            getCityByCoordinates = get(),
            userPreferences = get(),
            cityDvoMapper = get(),
        )
    }
    viewModel {
        CitySearchViewModel(
            searchCitiesByName = get(),
            cityDvoMapper = get(),
            userPreferences = get(),
        )
    }
    viewModel {
        WeatherViewModel(
            getHourlyWeatherData = get(),
            userPreferences = get(),
            weatherDvoMapper = get(),
        )
    }
}