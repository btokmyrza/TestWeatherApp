package kz.btokmyrza.weatherapp.presentation.mapper

import kz.btokmyrza.weatherapp.domain.model.Weather
import kz.btokmyrza.weatherapp.presentation.model.WeatherDvo
import kz.btokmyrza.weatherapp.presentation.model.WeatherTypeDvo
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

internal class WeatherDvoMapper {

    fun toWeatherDvo(from: Weather): WeatherDvo {
        val weatherDataMap = toWeatherDetailsMap(from = from)
        val currentTime = LocalDateTime.now()
        return WeatherDvo(
            weatherDataPerDay = weatherDataMap,
            currentWeatherData = weatherDataMap[0]?.find {
                val hour = if(currentTime.minute < 30) currentTime.hour else currentTime.hour + 1
                it.time.hour == hour
            },
        )
    }

    private fun toWeatherDetailsMap(from: Weather): Map<Int, List<WeatherDvo.WeatherDetailDvo>> =
        from.timestamps.mapIndexed { index, time ->
            val temperature = from.temperatures[index]
            val weatherCode = from.weatherCodes[index]
            val windSpeed = from.windSpeeds[index]
            val pressure = from.pressures[index]
            val humidity = from.humidities[index]
            IndexedWeatherData(
                index = index,
                data = WeatherDvo.WeatherDetailDvo(
                    time = LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME),
                    temperatureCelsius = temperature,
                    pressure = pressure,
                    windSpeed = windSpeed,
                    humidity = humidity,
                    weatherType = WeatherTypeDvo.toWeatherTypeDvo(weatherCode)
                )
            )
        }.groupBy { indexedWeatherData ->
            indexedWeatherData.index / 24
        }.mapValues { it ->
            it.value.map { it.data }
        }

    private class IndexedWeatherData(
        val index: Int,
        val data: WeatherDvo.WeatherDetailDvo,
    )
}