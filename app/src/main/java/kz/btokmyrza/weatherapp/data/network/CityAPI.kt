package kz.btokmyrza.weatherapp.data.network

import kz.btokmyrza.weatherapp.data.network.model.CityResponse
import retrofit2.http.GET
import retrofit2.http.Query

internal interface CityAPI {

    @GET("geo/1.0/direct")
    suspend fun searchCitiesByName(
        @Query("q") cityName: String,
        @Query("limit") limit: String,
        @Query("appid") apiKey: String,
    ): List<CityResponse>

    @GET("geo/1.0/reverse")
    suspend fun searchCitiesByCoordinates(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("appid") apiKey: String,
    ): List<CityResponse>
}