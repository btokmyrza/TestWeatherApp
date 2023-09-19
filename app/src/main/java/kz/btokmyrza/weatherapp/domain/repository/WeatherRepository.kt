package kz.btokmyrza.weatherapp.domain.repository

import kz.btokmyrza.weatherapp.domain.model.Weather

internal interface WeatherRepository {
    suspend fun getWeatherData(hourly: String, latitude: Double, longitude: Double): Weather
}