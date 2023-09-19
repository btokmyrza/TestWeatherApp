package kz.btokmyrza.weatherapp.data.mapper

import kz.btokmyrza.weatherapp.data.network.model.WeatherResponse
import kz.btokmyrza.weatherapp.domain.model.Weather

internal class WeatherModelMapper {

    fun toWeather(from: WeatherResponse): Weather = Weather(
        timestamps = from.hourlyWeatherResponse?.time.orEmpty(),
        temperatures = from.hourlyWeatherResponse?.temperatures.orEmpty(),
        weatherCodes = from.hourlyWeatherResponse?.weatherCodes.orEmpty(),
        pressures = from.hourlyWeatherResponse?.pressures.orEmpty(),
        windSpeeds = from.hourlyWeatherResponse?.windSpeeds.orEmpty(),
        humidities = from.hourlyWeatherResponse?.humidities.orEmpty(),
    )
}