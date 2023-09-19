package kz.btokmyrza.weatherapp.presentation.model

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

internal class WeatherDvo(
    val weatherDataPerDay: Map<Int, List<WeatherDetailDvo>>,
    val currentWeatherData: WeatherDetailDvo? = null,
) {

    class WeatherDetailDvo(
        val time: LocalDateTime,
        val temperatureCelsius: Double,
        val pressure: Double,
        val windSpeed: Double,
        val humidity: Double,
        val weatherType: WeatherTypeDvo,
    ) {

        fun getFormattedTime(): String = time.format(DateTimeFormatter.ofPattern("HH:mm"))

        fun getCurrentDate(): String = time.format(DateTimeFormatter.ofPattern("EEEE"))
    }
}