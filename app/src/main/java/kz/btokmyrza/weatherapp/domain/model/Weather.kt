package kz.btokmyrza.weatherapp.domain.model

internal class Weather(
    val timestamps: List<String>,
    val temperatures: List<Double>,
    val weatherCodes: List<Int>,
    val pressures: List<Double>,
    val windSpeeds: List<Double>,
    val humidities: List<Double>,
)