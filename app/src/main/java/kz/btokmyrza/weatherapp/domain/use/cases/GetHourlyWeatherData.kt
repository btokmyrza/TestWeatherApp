package kz.btokmyrza.weatherapp.domain.use.cases

import kz.btokmyrza.weatherapp.domain.model.Weather
import kz.btokmyrza.weatherapp.domain.repository.WeatherRepository

private val HOURLY_PARAMS = listOf(
    "temperature_2m",
    "weathercode",
    "relativehumidity_2m",
    "windspeed_10m",
    "pressure_msl",
).joinToString(",")

internal class GetHourlyWeatherData(
    private val repository: WeatherRepository,
) {

    suspend operator fun invoke(
        hourly: String = HOURLY_PARAMS,
        latitude: Double,
        longitude: Double,
    ): Weather = repository.getWeatherData(
        hourly = hourly,
        latitude = latitude,
        longitude = longitude,
    )
}