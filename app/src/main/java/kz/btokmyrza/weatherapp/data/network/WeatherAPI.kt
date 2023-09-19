package kz.btokmyrza.weatherapp.data.network

import kz.btokmyrza.weatherapp.data.network.model.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

internal interface WeatherAPI {

    @GET("v1/forecast")
    suspend fun getWeatherData(
        @Query("hourly") hourly: String,
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
    ): WeatherResponse
}