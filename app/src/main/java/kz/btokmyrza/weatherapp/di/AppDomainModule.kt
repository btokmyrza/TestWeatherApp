package kz.btokmyrza.weatherapp.di

import kz.btokmyrza.weatherapp.domain.use.cases.GetCityByCoordinates
import kz.btokmyrza.weatherapp.domain.use.cases.GetHourlyWeatherData
import kz.btokmyrza.weatherapp.domain.use.cases.SearchCitiesByName
import org.koin.dsl.module

internal val appDomainModule = module {
    factory { SearchCitiesByName(cityRepository = get()) }
    factory { GetCityByCoordinates(cityRepository = get()) }
    factory { GetHourlyWeatherData(repository = get()) }
}