package kz.btokmyrza.weatherapp.data.repository

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import kz.btokmyrza.weatherapp.data.mapper.CityModelMapper
import kz.btokmyrza.weatherapp.data.network.CityAPI
import kz.btokmyrza.weatherapp.domain.model.City
import kz.btokmyrza.weatherapp.domain.repository.CityRepository

internal class DefaultCityRepository(
    private val ioDispatcher: CoroutineDispatcher,
    private val cityAPI: CityAPI,
    private val cityModelMapper: CityModelMapper,
) : CityRepository {

    override suspend fun searchCitiesByName(
        cityName: String,
        limit: String,
        apiKey: String,
    ): List<City> = withContext(ioDispatcher) {
        cityAPI
            .searchCitiesByName(cityName = cityName, limit = limit, apiKey = apiKey)
            .map(cityModelMapper::toCity)
    }

    override suspend fun searchCitiesByCoordinates(
        latitude: Double,
        longitude: Double,
        apiKey: String,
    ): List<City> = withContext(ioDispatcher) {
        cityAPI
            .searchCitiesByCoordinates(latitude = latitude, longitude = longitude, apiKey = apiKey)
            .map(cityModelMapper::toCity)
    }
}