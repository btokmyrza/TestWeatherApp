package kz.btokmyrza.weatherapp.data.repository

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import kz.btokmyrza.weatherapp.data.mapper.WeatherModelMapper
import kz.btokmyrza.weatherapp.data.network.WeatherAPI
import kz.btokmyrza.weatherapp.domain.model.Weather
import kz.btokmyrza.weatherapp.domain.repository.WeatherRepository

internal class DefaultWeatherRepository(
    private val ioDispatcher: CoroutineDispatcher,
    private val weatherAPI: WeatherAPI,
    private val weatherModelMapper: WeatherModelMapper,
) : WeatherRepository {

    override suspend fun getWeatherData(
        hourly: String,
        latitude: Double,
        longitude: Double,
    ): Weather = withContext(ioDispatcher) {
        weatherAPI
            .getWeatherData(hourly = hourly, latitude = latitude, longitude)
            .let(weatherModelMapper::toWeather)
    }
}