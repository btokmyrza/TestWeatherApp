package kz.btokmyrza.weatherapp.domain.repository

import kz.btokmyrza.weatherapp.domain.model.City

internal interface CityRepository {

    suspend fun searchCitiesByName(cityName: String, limit:String, apiKey: String): List<City>

    suspend fun searchCitiesByCoordinates(
        latitude: Double,
        longitude: Double,
        apiKey: String,
    ): List<City>
}