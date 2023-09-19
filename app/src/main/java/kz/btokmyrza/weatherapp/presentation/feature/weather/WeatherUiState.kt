package kz.btokmyrza.weatherapp.presentation.feature.weather

import kz.btokmyrza.weatherapp.R
import kz.btokmyrza.weatherapp.presentation.model.WeatherDvo

internal data class WeatherUiState(
    val weatherInfo: WeatherDvo? = null,
    val cityName: String? = null,
    val latitude: Double? = null,
    val longitude: Double? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
) {

    fun getCurrentFormattedTime(): String =
        weatherInfo?.currentWeatherData?.getFormattedTime().orEmpty()

    fun getCurrentWeatherTypeIconRes(): Int =
        weatherInfo?.currentWeatherData?.weatherType?.iconRes ?: R.drawable.ic_sunny

    fun getCurrentWeatherDescription(): String =
        weatherInfo?.currentWeatherData?.weatherType?.description.orEmpty()

    fun getCurrentWeatherTemperature(): Double =
        weatherInfo?.currentWeatherData?.temperatureCelsius ?: 0.0

    fun getCurrentWeatherPressure(): Double =
        weatherInfo?.currentWeatherData?.pressure ?: 0.0

    fun getCurrentWeatherWindSpeed(): Double =
        weatherInfo?.currentWeatherData?.windSpeed ?: 0.0

    fun getCurrentWeatherHumidity(): Double =
        weatherInfo?.currentWeatherData?.humidity ?: 0.0

    fun getNextWeekWeatherData(): List<List<WeatherDvo.WeatherDetailDvo>> =
        weatherInfo?.weatherDataPerDay?.values?.toList().orEmpty()
}