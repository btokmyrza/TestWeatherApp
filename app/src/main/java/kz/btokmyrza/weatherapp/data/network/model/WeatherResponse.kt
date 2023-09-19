package kz.btokmyrza.weatherapp.data.network.model

import com.squareup.moshi.Json

internal class WeatherResponse(
    @field:Json(name = "hourly")
    val hourlyWeatherResponse: HourlyWeatherResponse? = null,
) {

    class HourlyWeatherResponse(
        @field:Json(name = "time")
        val time: List<String>? = null,
        @field:Json(name = "temperature_2m")
        val temperatures: List<Double>? = null,
        @field:Json(name = "weathercode")
        val weatherCodes: List<Int>? = null,
        @field:Json(name = "pressure_msl")
        val pressures: List<Double>? = null,
        @field:Json(name = "windspeed_10m")
        val windSpeeds: List<Double>? = null,
        @field:Json(name = "relativehumidity_2m")
        val humidities: List<Double>? = null,
    )
}